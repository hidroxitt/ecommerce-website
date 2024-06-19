package vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.auth.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    UserEntity findByUsernameAndEmail(String username, String email);
    Boolean existsByUsername(String username);
}
