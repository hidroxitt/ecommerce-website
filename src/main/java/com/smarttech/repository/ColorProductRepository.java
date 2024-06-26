package com.smarttech.repository;

import com.smarttech.entity.ColorProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ColorProductRepository extends JpaRepository<ColorProduct, Long> {


    @Modifying
    @Query("DELETE FROM ColorProduct cp WHERE cp.productId = ?1 AND cp.name NOT IN (?2)")
    void deleteColorByProductIdAndNotIn(Long productId, List<String> colors);

    @Query("SELECT cp FROM ColorProduct cp WHERE cp.productId = ?1 AND cp.name IN ?2")
    List<ColorProduct> findByProductIdAndColors(Long productId, List<String> colors);
}
