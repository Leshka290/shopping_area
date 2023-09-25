package com.skyteam.shopping_area.service.impl;

import com.skyteam.shopping_area.dto.RegisterDto;
import com.skyteam.shopping_area.repository.UserRepository;
import com.skyteam.shopping_area.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Реализует операции аутентификации пользователя
 *
 * @author youcanwakemeup
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManagerCustom manager;
    private final PasswordEncoder encoder;

    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterDto register) {
        manager.createUser(register);
        log.info("Зарегистрирован новый пользователь: " + register.getUsername());
        return true;
    }
}
