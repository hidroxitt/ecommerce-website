package vn.edu.hcmuaf.fit.shopzonerestfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.AdminEntity;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    Optional<AdminEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
}
