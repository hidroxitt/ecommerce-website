package vn.edu.hcmuaf.fit.shopzonerestfulapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String SKU;
    private String name;
    private double price;
    private int discount;
    private LocalDate offerEnd;
    private boolean isNew;
    private int rating;
    private int saleCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String shortDescription;
    private String fullDescription;
}
