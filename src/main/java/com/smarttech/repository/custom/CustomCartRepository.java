package com.smarttech.repository.custom;

import com.smarttech.constant.ImageType;
import com.smarttech.dto.cart.CartResponse;
import com.smarttech.entity.Cart;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomCartRepository {

    private final EntityManager entityManager;

    public List<CartResponse> getMyCart(String email, List<Long> cartIds) {
        String sql = new StringBuilder("SELECT c, p.name, pd.price, img.url, pd.percentDiscount, pd.size, cp.name, p.code, pd.quantity FROM Cart c")
                .append("  JOIN ProductDetail pd ON pd.id = c.productDetailId")
                .append("  JOIN Product p ON p.id = pd.productId")
                .append("  JOIN ColorProduct cp ON cp.id = pd.colorProductId")
                .append("  LEFT JOIN Image img ON img.objectId = cp.id AND img.type = :imageType")
                .append(" WHERE c.email = :email")
                .append(!CollectionUtils.isEmpty(cartIds) ? " AND c.id IN :cartIds" : "")
                .toString();
        Query<Tuple> query = entityManager.unwrap(Session.class).createQuery(sql, Tuple.class);
        query.setParameter("imageType", ImageType.PRODUCT_COLOR);
        if (!CollectionUtils.isEmpty(cartIds)) {
            query.setParameter("cartIds", cartIds);
        }
        query.setParameter("email", email);
        List<Tuple> resultList = query.getResultList();
        return resultList.stream()
                .map(tuple -> {
                    Cart cart = tuple.get(0, Cart.class);
                    return CartResponse.builder()
                            .cartId(cart.getId())
                            .quantity(cart.getQuantity())
                            .productName(tuple.get(1, String.class))
                            .price(tuple.get(2, BigDecimal.class))
                            .productDetailImage(tuple.get(3, String.class))
                            .discount(tuple.get(4, Integer.class))
                            .size(tuple.get(5, String.class))
                            .color(tuple.get(6, String.class))
                            .productCode(tuple.get(7, String.class))
                            .maxQuantity(tuple.get(8, Integer.class))
                            .build();
                })
                .collect(Collectors.toList());
    }
}
