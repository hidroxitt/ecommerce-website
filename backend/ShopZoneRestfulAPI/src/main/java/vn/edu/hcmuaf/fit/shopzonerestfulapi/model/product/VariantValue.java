package vn.edu.hcmuaf.fit.shopzonerestfulapi.model.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "variant_value")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariantValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
