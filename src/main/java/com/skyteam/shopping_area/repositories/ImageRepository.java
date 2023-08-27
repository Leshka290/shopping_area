package com.skyteam.shopping_area.repositories;

import com.skyteam.shopping_area.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByUserId(Long studentId);
    @Override
    List<Image> findAll();
}
