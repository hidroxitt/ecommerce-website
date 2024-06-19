package vn.edu.hcmuaf.fit.shopzonerestfulapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.product.Product;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;
    private String name;
    private String link;

    @ManyToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Product> product = new HashSet<>();
}
