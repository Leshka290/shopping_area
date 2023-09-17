package com.skyteam.shopping_area.service.impl;

import com.skyteam.shopping_area.dto.RegisterDto;
import com.skyteam.shopping_area.dto.Role;
import com.skyteam.shopping_area.exception.IncorrectUsernameException;
import com.skyteam.shopping_area.model.User;
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

    private final UserRepository userRepository;

    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterDto register) {
        createUser(register);
        log.info("Зарегистрирован новый пользователь: " + register.getUsername());
        return true;
    }

    private void createUser(RegisterDto register) {
        if (userRepository.findUserByEmailIgnoreCase(register.getUsername()).isPresent()) {
            throw new IncorrectUsernameException();
        }

        User user = new User();
        user.setEmail(register.getUsername());
        user.setPassword(encoder.encode(register.getPassword()));
        user.setRole(Role.USER);
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPhone(register.getPhone());
        log.info("Сохранение в БД user");
        log.info(String.valueOf(user));
        userRepository.save(user);
    }
}
