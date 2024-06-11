package vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    AdminEntity findByUsername(String username);
    Boolean existsByUsername(String username);
}
