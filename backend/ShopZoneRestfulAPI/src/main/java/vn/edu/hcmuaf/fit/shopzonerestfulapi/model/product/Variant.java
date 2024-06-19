package vn.edu.hcmuaf.fit.shopzonerestfulapi.model.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "variant")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Variant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
