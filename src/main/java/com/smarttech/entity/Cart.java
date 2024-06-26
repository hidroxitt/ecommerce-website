package com.smarttech.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "_cart")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "email")
    private String email;

    @Column(name = "product_detail_id")
    private Long productDetailId;
}
