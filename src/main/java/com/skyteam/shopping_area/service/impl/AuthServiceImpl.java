package com.skyteam.shopping_area.service.impl;

import com.skyteam.shopping_area.dto.RegisterDto;
import com.skyteam.shopping_area.dto.Role;
import com.skyteam.shopping_area.exception.IncorrectUsernameException;
import com.skyteam.shopping_area.model.User;
import com.skyteam.shopping_area.repository.UserRepository;
import com.skyteam.shopping_area.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

/**
 * Реализует операции аутентификации пользователя
 *
 * @author youcanwakemeup
 */

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    public AuthServiceImpl(UserDetailsManager manager,
                           PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.manager = manager;
        this.encoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterDto register) {
        if (manager.userExists(register.getUsername())) {
            return false;
        }
        manager.createUser(
                org.springframework.security.core.userdetails.User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(register.getPassword())
                        .username(register.getUsername())
                        .roles(register.getRole().name())
                        .build());
        createUser(register);
        return true;
    }

    private void createUser(RegisterDto register) {
        if (userRepository.findUserByEmail(register.getUsername()).isPresent()) {
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
