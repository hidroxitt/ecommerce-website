package com.smarttech.repository;

import com.smarttech.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

    @Query("SELECT pd FROM ProductDetail pd WHERE pd.id IN ?1")
    List<ProductDetail> findByIds(List<Long> productDetailIds);

    @Query("SELECT pd FROM ProductDetail pd WHERE pd.productId = ?1")
    List<ProductDetail> findByProductId(Long productId);

    @Modifying
    @Query("DELETE FROM ProductDetail pd WHERE pd.id IN ?1")
    void deleteByIds(List<Long> removeIds);
}
