package vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.auth.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    AdminEntity findByUsername(String username);
    Boolean existsByUsername(String username);
}
