package vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
