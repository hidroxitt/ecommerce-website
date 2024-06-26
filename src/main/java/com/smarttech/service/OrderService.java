package com.smarttech.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smarttech.constant.ImageType;
import com.smarttech.constant.NotificationConstant;
import com.smarttech.constant.OrderStatus;
import com.smarttech.constant.RoleConstant;
import com.smarttech.dto.order.OrderDetailResponse;
import com.smarttech.dto.order.OrderResponse;
import com.smarttech.dto.order.OrderSearchRequest;
import com.smarttech.dto.page.PageResponse;
import com.smarttech.dto.user.UserResponse;
import com.smarttech.entity.*;
import com.smarttech.exception.ValidationException;
import com.smarttech.factory.PaymentFactory;
import com.smarttech.repository.*;
import com.smarttech.repository.custom.CustomOrderRepository;
import com.smarttech.service.notify.INotifyService;
import com.smarttech.service.notify.model.Notification;
import com.smarttech.service.payment.IPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Value("${spring.mail.username}")
    private String adminEmail;

    private final CustomOrderRepository customOrderRepository;
    private final ObjectMapper objectMapper;
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderDetailRepository orderDetailRepository;
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ColorProductRepository colorProductRepository;

    private final PaymentFactory paymentFactory;

    @Setter(onMethod_ = @Autowired, onParam_ = @Qualifier("MailNotifyService"))
    private INotifyService notifyService;

    public PageResponse<OrderResponse> paginateOrderList(OrderSearchRequest request) {
        PageResponse<Order> paged = this.customOrderRepository.searchOrder(request);
        List<OrderResponse> orderResponses = paged.getItems().stream()
                .map(x -> this.objectMapper.convertValue(x, OrderResponse.class))
                .collect(Collectors.toList());
        return new PageResponse<>(paged, orderResponses);
    }

    public Map<String, Object> getTotalMoneyAndOrder() {
        UserResponse userResponse = this.userService.getCurrentUserThrowIfNot();
        return this.orderRepository.sumTotalOrderByUsername(userResponse.getUsername());
    }

    public OrderResponse getOrderResponse(String oCode) {
        UserResponse userResponse = this.userService.getCurrentUserThrowIfNot();
        return this.getOrderResponse(oCode, userResponse.getUsername());
    }

    public OrderResponse getOrderResponse(String oCode, String creator) {
        OrderResponse orderResponse = this.orderRepository.findByCodeAndCreatedBy(oCode, creator)
                .map(x -> this.objectMapper.convertValue(x, OrderResponse.class))
                .orElseThrow(() -> new ValidationException("Hóa đơn không tồn tại"));
        List<OrderDetailResponse> details = this.orderDetailRepository.findByOrderId(orderResponse.getId()).stream()
                .map(x -> {
                    OrderDetailResponse orderDetailResponse = new OrderDetailResponse();
                    orderDetailResponse.setId(x.getId());
                    orderDetailResponse.setCost(x.getCost());
                    orderDetailResponse.setPrice(x.getPrice());
                    orderDetailResponse.setDiscount(x.getPercentDiscount());
                    orderDetailResponse.setQuantity(x.getQuantity());

                    ProductDetail pDetail = this.productDetailRepository.findById(x.getProductDetailId())
                            .orElseThrow(() -> new ValidationException(""));
                    ColorProduct pColor = this.colorProductRepository.findById(pDetail.getColorProductId())
                            .orElseThrow(() -> new ValidationException(""));
                    Product p = this.productRepository.findById(pDetail.getProductId())
                            .orElseThrow(() -> new ValidationException(""));
                    this.imageRepository.findByTypeAndObjectId(ImageType.PRODUCT_COLOR, String.valueOf(pColor.getId()))
                            .map(Image::getUrl)
                            .ifPresent(orderDetailResponse::setProductImg);
                    orderDetailResponse.setProductName(p.getName());
                    orderDetailResponse.setProductCode(p.getCode());
                    orderDetailResponse.setSize(pDetail.getSize());
                    orderDetailResponse.setColor(pColor.getName());
                    return orderDetailResponse;
                })
                .collect(Collectors.toList());
        orderResponse.setOrderDetails(details);
        return orderResponse;
    }

    @Transactional
    public void changeStatusOrder(String orderCode, String note, OrderStatus status) {
        UserResponse userResponse = this.userService.getCurrentUserThrowIfNot();
        Order order = (RoleConstant.ADMIN.equals(userResponse.getRole())
                ? this.orderRepository.findByCode(orderCode)
                : this.orderRepository.findByCodeAndCreatedBy(orderCode, userResponse.getUsername()))
                .orElseThrow(() -> new ValidationException("Không tồn tại hóa đơn"));
        boolean ok = status.getCondition().test(order, userResponse);
        if (!ok) {
            throw new ValidationException("Không thể đổi trạng thái: " + status.getValue());
        }

        if (OrderStatus.APPROVED.equals(status) || OrderStatus.CANCEL.equals(status) || OrderStatus.REJECT.equals(status)) {
            List<ProductDetail> productDetails = this.checkQuantityProduct(order.getId(), status);
            this.productDetailRepository.saveAll(productDetails);
        }

        if (RoleConstant.USER.equals(userResponse.getRole())) {
            order.setUserNote(note);
        }

        if (RoleConstant.ADMIN.equals(userResponse.getRole())) {
            order.setAdminNote(note);
        }

        order.setStatus(status);
        this.orderRepository.save(order);

        if ((OrderStatus.CANCEL.equals(status) || OrderStatus.REJECT.equals(status))) {
            IPaymentService instance = this.paymentFactory.getInstance(order.getMethodPayment());
            instance.refund(order);
        }
        this.sendEmail(order);
    }

    public void sendEmail(Order order) {
        Notification notification = new Notification();
        notification.setFrom(this.adminEmail);
        notification.setDestination(order.getCreatedBy());
        notification.setTemplate(NotificationConstant.MailTemplate.ORDER);
        notification.setTitle("[No-REPLY] " + order.getStatus().getValue() + " đơn hàng " + order.getCode());
        notifyService.asyncSend(notification, additionalInfo -> {
            OrderResponse orderResponse = this.getOrderResponse(order.getCode(), order.getCreatedBy());
            orderResponse.setStatus(order.getStatus());
            additionalInfo.put("order", orderResponse);
        });
    }

    private List<ProductDetail> checkQuantityProduct(int orderId, OrderStatus orderStatus) {
        List<OrderDetail> details = this.orderDetailRepository.findByOrderId(orderId);
        List<Long> ids = details.stream()
                .map(OrderDetail::getProductDetailId)
                .collect(Collectors.toList());
        List<ProductDetail> productDetails = this.productDetailRepository.findByIds(ids);
        Map<Long, ProductDetail> pDetailMap = productDetails
                .stream()
                .collect(Collectors.toMap(ProductDetail::getId, x -> x));
        Predicate<OrderDetail> productHandler = d -> false;
        if (OrderStatus.APPROVED.equals(orderStatus)) {
            productHandler = this.handleProductApproveOrder(pDetailMap);
        } else if (OrderStatus.CANCEL.equals(orderStatus) || OrderStatus.REJECT.equals(orderStatus)) {
            productHandler = this.handleProductCancelOrder(pDetailMap);
        }
        String message = details
                .stream()
                .filter(productHandler)
                .map(d -> {
                    ProductDetail productDetail = pDetailMap.get(d.getProductDetailId());
                    ColorProduct colorProduct = this.colorProductRepository.findById(productDetail.getColorProductId()).get();
                    Product product = this.productRepository.findById(productDetail.getProductId()).get();
                    return String.format("%s (%s-%s) không đủ số lượng", product.getName(), colorProduct.getName(), productDetail.getSize());
                })
                .collect(Collectors.joining("<br>"));
        if (StringUtils.isNotBlank(message)) {
            throw new ValidationException(message);
        }
        return productDetails;
    }

    private Predicate<OrderDetail> handleProductCancelOrder(Map<Long, ProductDetail> pDetailMap) {
        return d -> {
            ProductDetail productDetail = pDetailMap.get(d.getProductDetailId());
            productDetail.setQuantity( productDetail.getQuantity() + d.getQuantity() );
            return false;
        };
    }

    private Predicate<OrderDetail> handleProductApproveOrder(Map<Long, ProductDetail> pDetailMap) {
        return d -> {
            ProductDetail productDetail = pDetailMap.get(d.getProductDetailId());
            if (productDetail.getQuantity() >= d.getQuantity()) {
                productDetail.setQuantity( productDetail.getQuantity() - d.getQuantity() );
                return false;
            }
            return true;
        };
    }

    public OrderResponse findByCode(String orderCode) {
        OrderResponse orderResponse = this.orderRepository.findByCode(orderCode)
                .map(x -> this.objectMapper.convertValue(x, OrderResponse.class))
                .orElseThrow(() -> new IllegalArgumentException(orderCode + " không tìm thấy"));
        List<OrderDetailResponse> od = this.customOrderRepository.findDetailByOrderId(orderResponse.getId());
        orderResponse.setOrderDetails(od);
        return orderResponse;
    }
}
