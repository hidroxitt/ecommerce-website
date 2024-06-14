package vn.edu.hcmuaf.fit.shopzonerestfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.SellerEntity;

public interface SellerRepository extends JpaRepository<SellerEntity, Long> {
    SellerEntity findByUsername(String username);
    Boolean existsByUsername(String username);
}
