package com.smarttech.service.payment;

import com.smarttech.constant.AppConstant;
import com.smarttech.constant.OrderStatus;
import com.smarttech.dto.order.CreateOrderRequest;
import com.smarttech.entity.*;
import com.smarttech.exception.ValidationException;
import com.smarttech.repository.*;
import com.smarttech.service.OrderService;
import com.smarttech.utils.CurrencyUtil;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Setter(onMethod_ = @Autowired)
public abstract class AbstractPaymentService implements IPaymentService {

    @Setter
    @Value("${payment.return-url}")
    protected String returnUrl;
    protected OrderRepository orderDao;
    protected CartRepository cartDao;
    protected ProductDetailRepository productDetailDao;
    protected ProductRepository productDao;
    protected ColorProductRepository colorProductDao;
    protected OrderDetailRepository orderDetailDao;
    protected OrderService orderService;

    public Order createOrder(CreateOrderRequest createOrderRequest) {
        List<Cart> carts = this.getMyCartByIds(createOrderRequest);
        if (carts.isEmpty()) {
            throw new ValidationException("Không tìm thấy sản phẩm");
        }
        Map<Long, ProductDetail> productDetailMap = this.getProductDetail(carts);
        this.validateQuantityProduct(carts, productDetailMap);
        Order order = new Order();
        order.setStatus(OrderStatus.PENDING);
        order.setNote(createOrderRequest.getNote());
        order.setAddress(createOrderRequest.getAddress());
        order.setCreatedBy(createOrderRequest.getCreatedBy());
        order.setPhone(createOrderRequest.getPhone());
        order.setFullName(createOrderRequest.getFullName());
        order.setMethodPayment(createOrderRequest.getMethod());
        order.setTotal(this.calculateTotalPrice(carts, productDetailMap));
        order.setPaymentOnlineId(createOrderRequest.getPaymentOnlineId());
        order.setPaymentOnlineDate(createOrderRequest.getPaymentOnlineDate());
        order = this.orderDao.save(order);

        for (Cart cart : carts) {
            ProductDetail productDetail = productDetailMap.get(cart.getProductDetailId());
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order.getId());
            orderDetail.setCost(productDetail.getCost());
            orderDetail.setQuantity(cart.getQuantity());
            orderDetail.setPrice(productDetail.getPrice());
            orderDetail.setPercentDiscount(productDetail.getPercentDiscount());
            orderDetail.setProductDetailId(productDetail.getId());
            this.orderDetailDao.save(orderDetail);
            productDetail.setQuantity(productDetail.getQuantity() - cart.getQuantity());
            this.productDetailDao.save(productDetail);
            this.cartDao.delete(cart);
        }
        return order;
    }

    protected List<Cart> getMyCartByIds(CreateOrderRequest createOrderRequest) {
        return this.cartDao.findByIdsAndEmail(createOrderRequest.getCartId(), createOrderRequest.getCreatedBy());
    }

    protected List<Long> getProductDetailIds(List<Cart> carts) {
        return carts.stream()
                .map(Cart::getProductDetailId)
                .collect(Collectors.toList());
    }

    protected Map<Long, ProductDetail> getProductDetail(List<Cart> carts) {
        List<Long> productDetailIds = this.getProductDetailIds(carts);
        return this.productDetailDao.findByIds(productDetailIds)
                .stream()
                .collect(Collectors.toMap(ProductDetail::getId, v -> v));
    }

    protected void validateQuantityProduct(List<Cart> carts, Map<Long, ProductDetail> productDetailMap) {
        String errorMessage = carts
                .stream()
                .filter(cart -> {
                    ProductDetail productDetail = productDetailMap.get(cart.getProductDetailId());
                    return cart.getQuantity() > productDetail.getQuantity();
                })
                .map(cart -> {
                    ProductDetail productDetail = productDetailMap.get(cart.getProductDetailId());
                    Product product = this.productDao.findById(productDetail.getProductId()).orElseThrow(() -> new ValidationException("Khong tim thay product"));
                    if (!product.getActive()) {
                        return String.format("%s không còn hỗ trợ", product.getName());
                    }
                    ColorProduct cp = this.colorProductDao.findById(productDetail.getColorProductId()).orElseThrow(() -> new ValidationException(""));
                    if (!productDetail.getActive()) {
                        return String.format("%s-%s-%s không còn hỗ trợ", product.getName(), cp.getName(), productDetail.getSize());
                    }
                    return String.format("%s-%s-%s không đủ số lượng", product.getName(), cp.getName(), productDetail.getSize());
                })
                .collect(Collectors.joining("</br>"));
        if (StringUtils.isNotBlank(errorMessage)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    protected BigDecimal calculateTotalPrice(List<Cart> carts, Map<Long, ProductDetail> productDetailMap) {
        return carts.stream()
                .map(cart -> {
                    ProductDetail productDetail = productDetailMap.get(cart.getProductDetailId());
                    return CurrencyUtil.calculateDiscountPrice(productDetail.getPrice(), productDetail.getPercentDiscount(), cart.getQuantity());
                })
                .reduce(AppConstant.ZERO, BigDecimal::add);
    }
}
