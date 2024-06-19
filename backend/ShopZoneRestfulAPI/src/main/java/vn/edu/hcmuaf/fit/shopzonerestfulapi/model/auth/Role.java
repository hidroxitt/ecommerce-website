package vn.edu.hcmuaf.fit.shopzonerestfulapi.model.auth;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "role")
    private Set<UserEntity> user = new HashSet<>();

    @OneToMany(mappedBy = "role")
    private Set<AdminEntity> admin = new HashSet<>();
}
