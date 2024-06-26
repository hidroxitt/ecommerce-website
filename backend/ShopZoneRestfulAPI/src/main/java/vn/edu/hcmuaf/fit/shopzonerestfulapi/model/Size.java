package vn.edu.hcmuaf.fit.shopzonerestfulapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "size")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variation_id")
    private Variation variation;
}
