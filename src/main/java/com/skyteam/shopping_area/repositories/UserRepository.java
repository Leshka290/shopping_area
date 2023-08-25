package com.skyteam.shopping_area.repositories;

import com.skyteam.shopping_area.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
