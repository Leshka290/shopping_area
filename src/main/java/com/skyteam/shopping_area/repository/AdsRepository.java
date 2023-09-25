package com.skyteam.shopping_area.repository;

import com.skyteam.shopping_area.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdsRepository extends JpaRepository<Ad, Integer> {

    List<Ad> findByAuthor_Email(String email);
}
