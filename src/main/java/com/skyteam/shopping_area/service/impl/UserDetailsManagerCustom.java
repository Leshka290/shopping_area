package com.skyteam.shopping_area.service.impl;

import com.skyteam.shopping_area.dto.RegisterDto;
import com.skyteam.shopping_area.dto.Role;
import com.skyteam.shopping_area.dto.UserDetailsCustom;
import com.skyteam.shopping_area.exception.IncorrectUsernameException;
import com.skyteam.shopping_area.model.User;
import com.skyteam.shopping_area.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Собственная реализация UserDetailsService
 *
 * @author leshka290
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsManagerCustom implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmailIgnoreCase(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new UserDetailsCustom(user);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public void createUser(RegisterDto register) {
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
