package vn.edu.hcmuaf.fit.shopzonerestfulapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "slider")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Slider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String subtitle;
    private String image;
    private String url;
}
