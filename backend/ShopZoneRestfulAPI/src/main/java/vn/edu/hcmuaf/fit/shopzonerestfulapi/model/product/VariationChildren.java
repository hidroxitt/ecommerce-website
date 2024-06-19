package vn.edu.hcmuaf.fit.shopzonerestfulapi.model.product;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "variation_children")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariationChildren {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String children;
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variation_parent_id")
    private VariationParent parent;
}
