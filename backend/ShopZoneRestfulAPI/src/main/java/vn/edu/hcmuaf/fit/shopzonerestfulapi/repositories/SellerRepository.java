package vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findOneById(Long id);
    Seller findByUsername(String username);
    Seller findByEmail(String email);
    Seller findByUsernameAndEmail(String username, String email);
}
