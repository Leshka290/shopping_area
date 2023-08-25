package com.skyteam.shopping_area.repositories;

import com.skyteam.shopping_area.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
