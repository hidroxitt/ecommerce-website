package com.smarttech.repository;

import com.smarttech.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @Modifying
    void deleteByEmail(String email);

    @Query("SELECT c FROM Cart c WHERE c.email = ?1 AND c.productDetailId = ?2")
    Optional<Cart> findByEmailAndProductDetailId(String email, Long productDetailId);

    @Query("SELECT c FROM Cart c WHERE c.id IN ?1 AND c.email = ?2")
    List<Cart> findByIdsAndEmail(List<Long> ids, String email);
}
