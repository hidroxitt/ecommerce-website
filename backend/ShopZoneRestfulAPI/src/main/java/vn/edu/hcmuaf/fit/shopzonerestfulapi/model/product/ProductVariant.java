package vn.edu.hcmuaf.fit.shopzonerestfulapi.model.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_variant")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
