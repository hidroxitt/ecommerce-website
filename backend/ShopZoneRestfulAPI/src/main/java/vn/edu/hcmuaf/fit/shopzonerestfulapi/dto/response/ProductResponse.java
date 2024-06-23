package vn.edu.hcmuaf.fit.shopzonerestfulapi.dto.response;

import lombok.Data;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductResponse {
    private long id;
    private String name;
    private BigDecimal price;
    private int discount;
    private LocalDate offerEnd;
    private boolean isNew;
    private int rating;
    private int saleCount;
    private List<ReviewResponse> reviews;
    private String shortDescription;
    private String fullDescription;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.discount = product.getDiscount();
        this.offerEnd = product.getOfferEnd();
        this.rating = product.getRating();
        this.saleCount = product.getSaleCount();
        this.reviews = product.getReviews().stream()
                .map(ReviewResponse::new)
                .collect(Collectors.toList());
        this.shortDescription = product.getShortDescription();
        this.fullDescription = product.getFullDescription();
    }
}
