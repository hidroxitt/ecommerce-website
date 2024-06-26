package com.smarttech.repository;

import com.smarttech.constant.ImageType;
import com.smarttech.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("SELECT img FROM Image img WHERE img.type = ?1 AND img.objectId = ?2")
    Optional<Image> findByTypeAndObjectId(ImageType product, String objectId);
}
