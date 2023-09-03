package com.skyteam.shopping_area.repository;

import com.skyteam.shopping_area.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdsRepository extends JpaRepository<Ad, Integer> {
}
