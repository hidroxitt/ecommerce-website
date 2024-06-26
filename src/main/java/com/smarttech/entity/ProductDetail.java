package com.smarttech.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "_product_detail")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "color_product_id")
    private Long colorProductId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "size")
    private String size;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "percent_discount")
    private Integer percentDiscount;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "product_id")
    private long productId;
}
