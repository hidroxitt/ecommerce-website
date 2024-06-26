package vn.edu.hcmuaf.fit.shopzonerestfulapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findAllById(Long id);
}
