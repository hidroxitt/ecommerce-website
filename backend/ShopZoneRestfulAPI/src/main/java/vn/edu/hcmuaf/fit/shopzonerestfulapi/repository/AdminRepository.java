package vn.edu.hcmuaf.fit.shopzonerestfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.AdminEntity;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.Role;

import java.util.Set;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    AdminEntity findByUsername(String username);
    Boolean existsByUsername(String username);
}
