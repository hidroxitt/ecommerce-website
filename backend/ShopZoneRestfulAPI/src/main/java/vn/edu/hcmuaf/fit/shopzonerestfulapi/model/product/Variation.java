package vn.edu.hcmuaf.fit.shopzonerestfulapi.model.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "variation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Variation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String variation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "variation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<VariationParent> parentList;
}
