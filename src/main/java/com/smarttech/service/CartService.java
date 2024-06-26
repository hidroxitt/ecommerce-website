package com.smarttech.service;

import com.smarttech.dto.cart.AddToCartRequest;
import com.smarttech.dto.cart.CartResponse;
import com.smarttech.dto.cart.UpdateCart;
import com.smarttech.dto.user.UserResponse;
import com.smarttech.entity.Cart;
import com.smarttech.entity.ColorProduct;
import com.smarttech.entity.Product;
import com.smarttech.entity.ProductDetail;
import com.smarttech.exception.ValidationException;
import com.smarttech.repository.CartRepository;
import com.smarttech.repository.ColorProductRepository;
import com.smarttech.repository.ProductDetailRepository;
import com.smarttech.repository.ProductRepository;
import com.smarttech.repository.custom.CustomCartRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CustomCartRepository customCartRepository;
    private final CartRepository cartDao;
    private final ProductDetailRepository productDetailDao;
    private final ProductRepository productDao;
    private final ColorProductRepository colorProductDao;

    private final UserService userService;

    public List<CartResponse> getMyCart() {
        UserResponse currentUserThrowIfNot = this.userService.getCurrentUserThrowIfNot();
        return this.customCartRepository.getMyCart(currentUserThrowIfNot.getEmail(), null);
    }

    public List<CartResponse> getMyCart(List<Long> ids) {
        UserResponse currentUserThrowIfNot = this.userService.getCurrentUserThrowIfNot();
        return this.customCartRepository.getMyCart(currentUserThrowIfNot.getEmail(), ids);
    }

    @Transactional
    public void deleteById(Long cartId) {
        Cart cart = this.cartDao.findById(cartId)
                .orElseThrow(() -> new ValidationException("Không tìm thấy"));
        UserResponse userResponse = this.userService.getCurrentUserThrowIfNot();
        if (!cart.getEmail().equals(userResponse.getEmail())) {
            throw new ValidationException("Bạn không có quyền xóa sản phẩm");
        }
        this.cartDao.deleteById(cartId);
    }

    @Transactional
    public void deleteAll() {
        UserResponse currentUserThrowIfNot = this.userService.getCurrentUserThrowIfNot();
        this.cartDao.deleteByEmail(currentUserThrowIfNot.getEmail());
    }

    @Transactional
    public void addToCart(AddToCartRequest request) {
        ProductDetail productDetail = this.productDetailDao.findById(request.getProductDetailId())
                .orElseThrow(() -> new ValidationException("Không tìm thấy sản phẩm"));
        if (!productDetail.getActive()) {
            throw new ValidationException("Sản phẩm không được phép");
        }
        UserResponse currentUserThrowIfNot = this.userService.getCurrentUserThrowIfNot();
        Cart cart = this.cartDao.findByEmailAndProductDetailId(currentUserThrowIfNot.getEmail(), request.getProductDetailId())
                .map(c -> {
                    c.setQuantity(c.getQuantity() + request.getQuantity());
                    return c;
                })
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setQuantity(request.getQuantity());
                    c.setProductDetailId(productDetail.getId());
                    c.setEmail(currentUserThrowIfNot.getEmail());
                    return c;
                });
        if (productDetail.getQuantity() < cart.getQuantity()) {
            throw new ValidationException("Số lượng sản phẩm không đủ");
        }
        this.cartDao.save(cart);
    }

    @Transactional
    public void updateCart(List<UpdateCart> updateCarts) {
        UserResponse userResponse = this.userService.getCurrentUserThrowIfNot();
        List<Long> ids = updateCarts.stream()
                .map(UpdateCart::getId)
                .collect(Collectors.toList());
        Map<Long, Cart> cartMap = this.cartDao.findByIdsAndEmail(ids, userResponse.getEmail())
                .stream()
                .collect(Collectors.toMap(Cart::getId, c -> c));
        List<Long> productDetailIds = cartMap.values().stream()
                .map(Cart::getProductDetailId)
                .collect(Collectors.toList());
        Map<Long, ProductDetail> productDetailMap = this.productDetailDao.findByIds(productDetailIds)
                .stream()
                .collect(Collectors.toMap(ProductDetail::getId, pd -> pd));

        this.validateBeforeUpdateCart(updateCarts, cartMap, productDetailMap);

        updateCarts.forEach(c -> {
            Cart cart = cartMap.get(c.getId());
            if (Objects.nonNull(cart)) {
                cart.setQuantity(c.getQuantity());
                this.cartDao.save(cart);
            }
        });
    }

    private void validateBeforeUpdateCart(List<UpdateCart> updateCarts, Map<Long, Cart> cartMap, Map<Long, ProductDetail> productDetailMap) {
        String messageError = updateCarts.stream()
                .filter(c -> cartMap.get(c.getId()) != null)
                .map(c -> {
                    Cart cart = cartMap.get(c.getId());
                    ProductDetail productDetail = productDetailMap.get(cart.getProductDetailId());
                    return c.getQuantity() > productDetail.getQuantity() ? productDetail : null;
                })
                .filter(Objects::nonNull)
                .map(pd -> {
                    Product product = this.productDao.findById(pd.getProductId())
                            .orElseThrow(() -> new ValidationException(""));
                    ColorProduct colorProduct = this.colorProductDao.findById(pd.getColorProductId())
                            .orElseThrow(() -> new ValidationException(""));
                    return String.format("%s-%s-%s không đủ số lượng", product.getName(), colorProduct.getName(), pd.getSize());
                })
                .collect(Collectors.joining("</br>"));
        if (StringUtils.isNotBlank(messageError)) {
            throw new ValidationException(messageError);
        }
    }
}
