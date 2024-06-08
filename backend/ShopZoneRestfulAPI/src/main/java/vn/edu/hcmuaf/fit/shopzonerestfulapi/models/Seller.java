package vn.edu.hcmuaf.fit.shopzonerestfulapi.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sellers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String avatar;
    private String address;
    private Integer status;
    private String token;

}
