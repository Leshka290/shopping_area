package com.skyteam.shopping_area.services.impl;

import com.skyteam.shopping_area.repositories.UserRepository;
import com.skyteam.shopping_area.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Реализует CRUD операции с пользователями класса User
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
