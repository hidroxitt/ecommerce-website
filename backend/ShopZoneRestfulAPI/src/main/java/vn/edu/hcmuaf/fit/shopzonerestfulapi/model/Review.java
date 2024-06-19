package vn.edu.hcmuaf.fit.shopzonerestfulapi.model;

import jakarta.persistence.*;
import lombok.*;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.product.Product;

import java.time.LocalDate;

@Entity
@Table(name = "review")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private String username;
    private int rating;
    private String comment;
    private LocalDate reviewDate;
}
