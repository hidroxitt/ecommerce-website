package vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneById(Long id);
    User findByUsername(String username);
    User findByEmail(String email);
    User findByUsernameOrEmail(String username, String email);
}
