package com.smarttech.repository;

import com.smarttech.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT SUM(o.total) as totalMoney, COUNT(o) as totalOrder FROM Order o WHERE o.createdBy = ?1 AND o.status = 'DONE'")
    Map<String, Object> sumTotalOrderByUsername(String username);

    @Query("SELECT o FROM Order o WHERE o.code = ?1 AND o.createdBy = ?2")
    Optional<Order> findByCodeAndCreatedBy(String oCode, String username);

    Optional<Order> findByCode(String code);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.createdDate = :createdDate")
    long countByDate(@Param("createdDate") Date date);
}
