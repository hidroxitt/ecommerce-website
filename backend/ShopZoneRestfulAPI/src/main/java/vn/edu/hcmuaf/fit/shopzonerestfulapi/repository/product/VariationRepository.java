package vn.edu.hcmuaf.fit.shopzonerestfulapi.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmuaf.fit.shopzonerestfulapi.model.product.Variation;

public interface VariationRepository extends JpaRepository<Variation, Long> {
}
