package com.smarttech.repository;

import com.smarttech.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCode(String code);

    @Query("SELECT c FROM Category c WHERE c.active = true")
    List<Category> findActiveCategory();

    @Query("SELECT c FROM Category c WHERE c.active = true AND c.parentId IS NULL")
    List<Category> findRootActiveCategory();

    List<Category> findByParentIdAndActive(Long parentId, Boolean active);

    @Query("SELECT c FROM Category c WHERE c.parentId is not null and c.active = true")
    List<Category> findActiveChildCategory();

    List<Category> findByParentId(Long parentId);
}
