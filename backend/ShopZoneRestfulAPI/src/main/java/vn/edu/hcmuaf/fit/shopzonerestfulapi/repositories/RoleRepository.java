package vn.edu.hcmuaf.fit.shopzonerestfulapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
