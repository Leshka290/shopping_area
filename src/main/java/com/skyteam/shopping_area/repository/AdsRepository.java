package com.skyteam.shopping_area.repository;

import com.skyteam.shopping_area.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdsRepository extends JpaRepository<Ad, Long> {
    default void delete(Optional<Ad> byId) {}


    List<Ad> findByAuthorId(String authenticatedUserId);
}
