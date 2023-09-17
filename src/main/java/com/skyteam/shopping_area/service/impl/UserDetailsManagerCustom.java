package com.skyteam.shopping_area.service.impl;

import com.skyteam.shopping_area.dto.UserDetailsCustom;
import com.skyteam.shopping_area.model.User;
import com.skyteam.shopping_area.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmail(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new UserDetailsCustom(user);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
