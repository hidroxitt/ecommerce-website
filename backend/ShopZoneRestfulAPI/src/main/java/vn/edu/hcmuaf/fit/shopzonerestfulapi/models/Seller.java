package vn.edu.hcmuaf.fit.shopzonerestfulapi.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

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

    private LocalDate dob;

    private String avatar;

    private String address;

    private Integer status;

    private String token;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "seller_roles",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

}
