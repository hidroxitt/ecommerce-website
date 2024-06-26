package com.smarttech.repository.custom;

import com.smarttech.constant.AppConstant;
import com.smarttech.constant.ImageType;
import com.smarttech.dto.dashboard.Chart;
import com.smarttech.dto.page.PageResponse;
import com.smarttech.dto.product.*;
import com.smarttech.entity.ColorProduct;
import com.smarttech.entity.ProductDetail;
import com.smarttech.utils.PagingUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomProductRepository {

    private final Session session;

    public PageResponse<ProductResponse> searchProduct(ProductSearchRequest data) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT p.*, c.name as categoryName, i.url as image, MAX(pd.price) as maxPrice, MIN(pd.price) as minPrice, MAX(pd.percent_discount) as discount")
                .append(" FROM _product p")
                .append("  JOIN _category c ON c.id = p.category_id")
                .append("  JOIN _image i ON i.type = 'PRODUCT' AND i.object_id = p.id")
                .append("  JOIN _product_detail pd ON pd.product_id = p.id")
                .append(" WHERE 1 = 1");
        Map<String, Object> params = new HashMap<>();

        if (StringUtils.isNotBlank(data.getCodeName())) {
            sqlBuilder.append(" AND (p.code LIKE :codeName OR p.name LIKE :codeName)");
            params.put("codeName", "%" + data.getCodeName() + "%");
        }

        if (Objects.nonNull(data.getCategoryId())) {
            sqlBuilder.append(" AND p.category_id = :categoryId");
            params.put("categoryId", data.getCategoryId());
        }

        if (StringUtils.isNotBlank(data.getFromDate())) {
            sqlBuilder.append(" AND p.created_date >= :fromDate");
            params.put("fromDate", data.getFromDate());
        }

        if (StringUtils.isNotBlank(data.getToDate())) {
            sqlBuilder.append(" AND p.created_date <= :toDate");
            params.put("toDate", data.getToDate());
        }

        if (StringUtils.isNotBlank(data.getSize())) {
            sqlBuilder.append(" AND pd.size = :size");
            params.put("size", data.getSize());
        }

        if (Objects.nonNull(data.getActive())) {
            sqlBuilder.append(" AND p.active = :active");
            params.put("active", data.getActive());
        }

        if (Objects.nonNull(data.getMinPrice()) || Objects.nonNull(data.getMaxPrice())) {
            sqlBuilder.append(" HAVING 1 = 1");
            if (Objects.nonNull(data.getMinPrice())) {
                sqlBuilder.append(" AND minPrice >= :minPrice");
                params.put("minPrice", data.getMinPrice());
            }

            if (Objects.nonNull(data.getMaxPrice())) {
                sqlBuilder.append(" AND maxPrice <= :maxPrice");
                params.put("maxPrice", data.getMaxPrice());
            }
        }

        sqlBuilder.append(" GROUP BY p.code");
        if (Objects.nonNull(data.getSort()) && StringUtils.isNotBlank(data.getColumnSort())) {
            sqlBuilder.append(" ORDER BY ")
                    .append(data.getColumnSort())
                    .append(StringUtils.SPACE)
                    .append(data.getSort().name());
        } else {
            sqlBuilder.append(" ORDER BY p.created_date DESC");
        }
        return PagingUtil.paginate(sqlBuilder.toString(), params, data, tuple -> {
        	List<TupleElement<?>> elements = tuple.getElements();
            ProductResponse build = ProductResponse.builder()
                    .id(tuple.get("id", BigInteger.class).longValue())
                    .active(tuple.get("active", Boolean.class))
                    .categoryId(tuple.get("category_id", BigInteger.class).longValue())
                    .code(tuple.get("code", String.class))
                    .description(tuple.get("description", String.class))
                    .isShowHome(tuple.get("is_show_home", Boolean.class))
                    .name(tuple.get("name", String.class))
                    .shortDescription(tuple.get(9, String.class))
                    .slug(tuple.get("slug", String.class))
                    .categoryName(tuple.get("categoryName", String.class))
                    .image(tuple.get("image", String.class))
                    .maxPrice(tuple.get("maxPrice", BigDecimal.class))
                    .minPrice(tuple.get("minPrice", BigDecimal.class))
                    .discount(tuple.get("discount", Integer.class))
                    .build();
            return build;
        }).apply(this.session);
    }

    public List<ProductImageDTO> getProductImage(int limit) {
        String sql = new StringBuilder("SELECT p.id, p.name, p.code, i.url, p.slug FROM Product p")
                .append(" JOIN Image i ON p.id = i.objectId")
                .append(" WHERE i.type = 'PRODUCT' AND p.active = true AND p.isShowHome = true")
                .toString();
        Query<Tuple> query = session.createQuery(sql, Tuple.class);
        query.setMaxResults(limit);
        List<Tuple> resultList = query.getResultList();
        return resultList.stream()
                .map(tuple -> ProductImageDTO.builder()
                        .productId(tuple.get(0, Long.class))
                        .productName(tuple.get(1, String.class))
                        .productCode(tuple.get(2, String.class))
                        .imageUrl(tuple.get(3, String.class))
                        .slug(tuple.get(4, String.class))
                        .build()
                )
                .collect(Collectors.toList());
    }

    public List<ProductResponse> getByCategoryId(int limit, List<Long> categoryId) {
        String sql = new StringBuilder("SELECT p.id, p.code, p.name, img.url, MIN(pd.price), MAX(pd.price), p.short_description, p.slug, MAX(pd.percent_discount)")
                .append(" FROM _product p")
                .append("  JOIN _image img ON img.object_id = p.id")
                .append("  JOIN _product_detail pd ON p.id = pd.product_id")
                .append(" WHERE img.type = 'PRODUCT' AND p.active = true AND p.category_id IN ?1")
                .append(" GROUP BY p.code")
                .append(" ORDER BY p.created_date DESC")
                .toString();
        return this.getProductInUserHome(sql, limit, categoryId);
    }

    public List<ProductResponse> getBestSeller(int limit) {
        String sql = new StringBuilder("SELECT p.id, p.code, p.name, img.url, MIN(pd.price), MAX(pd.price), p.short_description, p.slug, MAX(pd.percent_discount), count(1) as count")
                .append(" FROM _product p")
                .append("  JOIN _image img ON img.object_id = p.id")
                .append("  JOIN _product_detail pd ON p.id = pd.product_id")
                .append("  JOIN _order_detail od ON pd.id = od.product_detail_id")
                .append(" WHERE img.type = 'PRODUCT' AND p.active = true")
                .append(" GROUP BY p.code")
                .append(" ORDER BY count DESC")
                .toString();
        return this.getProductInUserHome(sql, limit);
    }

    public List<ProductResponse> getNewest(int limit) {
        String sql = new StringBuilder("SELECT p.id, p.code, p.name, img.url, MIN(pd.price), MAX(pd.price), p.short_description, p.slug, MAX(pd.percent_discount)")
                .append(" FROM _product p")
                .append("  JOIN _image img ON img.object_id = p.id")
                .append("  JOIN _product_detail pd ON p.id = pd.product_id")
                .append(" WHERE img.type = 'PRODUCT' AND p.active = true AND DATEDIFF(now(), p.created_date) <= ?1")
                .append(" GROUP BY p.code")
                .append(" ORDER BY p.created_date DESC")
                .toString();
        return this.getProductInUserHome(sql, limit, AppConstant.DATE_INDICATE_NEWEST);
    }

    public List<ProductResponse> getHotSales(int limit) {
        String sql = new StringBuilder("SELECT p.id, p.code, p.name, img.url, MIN(pd.price), MAX(pd.price), p.short_description , p.slug, MAX(pd.percent_discount) as discount")
                .append(" FROM _product p")
                .append("  JOIN _image img ON img.object_id = p.id")
                .append("  JOIN _product_detail pd ON p.id = pd.product_id")
                .append(" WHERE img.type = 'PRODUCT' AND p.active = true")
                .append(" GROUP BY p.code")
                .append(" HAVING discount >= ?1")
                .append(" ORDER BY discount DESC")
                .toString();
        return this.getProductInUserHome(sql, limit, AppConstant.PERCENT_INDICATE_HOT_SALE);
    }

    private List<ProductResponse> getProductInUserHome(String sql, int limit, Object... params) {
        Function<Tuple, ProductResponse> mapper = tuple -> ProductResponse.builder()
                .id(tuple.get(0, BigInteger.class).longValue())
                .code(tuple.get(1, String.class))
                .name(tuple.get(2, String.class))
                .image(tuple.get(3, String.class))
                .minPrice(tuple.get(4, BigDecimal.class))
                .maxPrice(tuple.get(5, BigDecimal.class))
                .shortDescription(tuple.get(6, String.class))
                .slug(tuple.get(7, String.class))
                .discount(tuple.get(8, Integer.class))
                .build();
        return this.getProductInUserHome(sql, limit, mapper, params);
    }

    private List<ProductResponse> getProductInUserHome(String sql, int limit,  Function<Tuple, ProductResponse> mapper, Object...params) {
        Query<Tuple> query = session.createNativeQuery(sql, Tuple.class);
        if (Objects.nonNull(params)) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i + 1, params[i]);
            }
        }
        query.setMaxResults(limit);
        List<Tuple> resultList = query.getResultList();
        return resultList.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    public Map<ColorProductDTO, List<ProductDetailRequest>> getSizeAndColorProduct(long productId) {
        String sql = new StringBuilder("SELECT cd, pd, img.url as imageUrl FROM ProductDetail pd")
                .append(" JOIN ColorProduct cd ON pd.colorProductId = cd.id")
                .append(" LEFT JOIN Image img ON img.objectId = cd.id")
                .append(" WHERE pd.productId = :productId AND img.type = :type")
                .toString();
        Query<Tuple> query = session.createQuery(sql, Tuple.class);
        query.setParameter("productId", productId);
        query.setParameter("type", ImageType.PRODUCT_COLOR);
        List<Tuple> resultList = query.getResultList();
        return resultList.stream()
                .map(tuple -> {
                    ColorProduct colorProduct = tuple.get(0, ColorProduct.class);
                    ProductDetail productDetail = tuple.get(1, ProductDetail.class);
                    return ProductDetailRequest.builder()
                            .id(productDetail.getId())
                            .cost(productDetail.getCost())
                            .price(productDetail.getPrice())
                            .size(productDetail.getSize())
                            .quantity(productDetail.getQuantity())
                            .discount(productDetail.getPercentDiscount())
                            .active(productDetail.getActive())
                            .color(ColorProductDTO.builder()
                                    .color(colorProduct.getName())
                                    .id(colorProduct.getId())
                                    .imageUrl(tuple.get("imageUrl", String.class))
                                    .build())
                            .build();
                })
                .collect(Collectors.groupingBy(ProductDetailRequest::getColor, Collectors.toList()));
    }

    public List<Chart> statisticProduct(Integer month, Integer year) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT p.name, count(1) FROM _product p")
                .append("  JOIN _product_detail pd ON pd.product_id = p.id")
                .append("  JOIN _order_detail od ON od.product_detail_id = pd.id")
                .append("  JOIN _order o ON o.id = od.order_id")
                .append(" WHERE 1 = 1");
        Map<String, Object> param = new HashMap<>();
        if (Objects.nonNull(month)) {
            sqlBuilder.append(" AND MONTH(o.created_date) = :month");
            param.put("month", month);
        }

        if (Objects.nonNull(year)) {
            sqlBuilder.append(" AND YEAR(o.created_date) = :year");
            param.put("year", year);
        }
        sqlBuilder.append(" GROUP BY p.id");

        Query<Tuple> query = session.createNativeQuery(sqlBuilder.toString(), Tuple.class);
        param.forEach(query::setParameter);
        return query.getResultList()
                .stream()
                .map(tutple -> {
                    String categoryName = tutple.get(0, String.class);
                    BigInteger count = tutple.get(1, BigInteger.class);
                    return new Chart(categoryName, count.toString());
                })
                .collect(Collectors.toList());
    }
}
