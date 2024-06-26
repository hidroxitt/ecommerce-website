package com.smarttech.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "_specifications")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Specifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name")
    private String key;

    @Column(name = "_value")
    private String value;

    @Column(name = "product_id")
    private Long productId;
}
