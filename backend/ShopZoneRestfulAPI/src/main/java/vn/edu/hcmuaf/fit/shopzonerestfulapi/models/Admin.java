package vn.edu.hcmuaf.fit.shopzonerestfulapi.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admins")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String avatar;
    private String token;


}
