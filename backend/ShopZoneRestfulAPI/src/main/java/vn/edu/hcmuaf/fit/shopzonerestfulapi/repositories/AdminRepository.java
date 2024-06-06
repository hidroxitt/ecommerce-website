package vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findOneById(Long id);
    Admin findByUsername(String username);
    Admin findByEmail(String email);
    Admin findByUsernameAndEmail(String username, String email);
}
