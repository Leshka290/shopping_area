package com.skyteam.shopping_area.repository;

import com.skyteam.shopping_area.model.Ad;
import com.skyteam.shopping_area.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdsRepository extends JpaRepository<Ad, Integer> {

    List<Ad> findAllByAuthor(User user);
}
