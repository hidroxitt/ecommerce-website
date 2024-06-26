package com.smarttech.repository;

import com.smarttech.entity.Specifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface SpecificationsRepository extends JpaRepository<Specifications, Long> {

    List<Specifications> findByProductId(Long productId);

    @Modifying
    void deleteByProductId(Long productId);
}
