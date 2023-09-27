package com.skyteam.shopping_area.repository;

import com.skyteam.shopping_area.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    Optional<Image> findByFilePath(String filePath);
}
