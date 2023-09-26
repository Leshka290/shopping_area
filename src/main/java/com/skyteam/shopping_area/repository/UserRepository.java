package com.skyteam.shopping_area.repository;

import com.skyteam.shopping_area.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmailIgnoreCase(String email);
    Optional<User> findUserByEmail(String email);
}
