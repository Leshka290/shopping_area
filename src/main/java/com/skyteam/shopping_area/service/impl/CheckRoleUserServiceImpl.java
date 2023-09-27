package com.skyteam.shopping_area.service.impl;

import com.skyteam.shopping_area.dto.Role;
import com.skyteam.shopping_area.exception.UserNotFoundException;
import com.skyteam.shopping_area.model.User;
import com.skyteam.shopping_area.repository.UserRepository;
import com.skyteam.shopping_area.service.CheckRoleUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CheckRoleUserServiceImpl implements CheckRoleUserService {

    private final UserRepository userRepository;

    @Override
    public boolean isUser(String email, User owner) {
        if (email.equals(owner.getEmail())) {
            log.info("Is owner");
            return true;
        } else {
            log.info("Is not Owner");
            return false;
        }
    }

    @Override
    public boolean isAdmin(String email) {
        User user = userRepository.findUserByEmailIgnoreCase(email).orElseThrow(UserNotFoundException::new);
        if (user.getRole() == (Role.ADMIN)) {
            log.info("Is admin");
            return true;
        } else {
            log.info("Is not admin");
            return false;
        }
    }

    @Override
    public boolean isUserOrAdmin(String email, User ownerAds) {
        return isUser(email, ownerAds) || isAdmin(email);
    }
}
