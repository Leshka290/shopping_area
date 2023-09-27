package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.dto.Role;
import com.skyteam.shopping_area.exception.UserNotFoundException;
import com.skyteam.shopping_area.model.User;
import com.skyteam.shopping_area.repository.UserRepository;
import com.skyteam.shopping_area.service.CheckRoleUserService;
import com.skyteam.shopping_area.service.impl.CheckRoleUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckRoleUserServiceTest {

    @Mock
    private UserRepository userRepository;

    private CheckRoleUserService checkRoleUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        checkRoleUserService = new CheckRoleUserServiceImpl(userRepository);
    }

    @Test
    void testIsUser_UserIsOwner() {
        String email = "testuser@test.com";
        User owner = new User();
        owner.setEmail(email);

        boolean isUserResult = checkRoleUserService.isUser(email, owner);

        assertTrue(isUserResult);
    }

    @Test
    void testIsUser_UserIsNotOwner() {
        String email = "testuser@test.com";
        String ownerEmail = "owner@test.com";
        User owner = new User();
        owner.setEmail(ownerEmail);

        boolean isUserResult = checkRoleUserService.isUser(email, owner);

        assertFalse(isUserResult);
    }

    @Test
    void testIsAdmin_UserIsAdmin() {
        String email = "admin@test.com";
        User adminUser = new User();
        adminUser.setEmail(email);
        adminUser.setRole(Role.ADMIN);

        when(userRepository.findUserByEmailIgnoreCase(email)).thenReturn(Optional.of(adminUser));

        boolean isAdminResult = checkRoleUserService.isAdmin(email);

        assertTrue(isAdminResult);
    }

    @Test
    void testIsAdmin_UserIsNotAdmin() {
        String email = "regular@test.com";
        User regularUser = new User();
        regularUser.setEmail(email);
        regularUser.setRole(Role.USER);

        when(userRepository.findUserByEmailIgnoreCase(email)).thenReturn(Optional.of(regularUser));

        boolean isAdminResult = checkRoleUserService.isAdmin(email);

        assertFalse(isAdminResult);
    }

    @Test
    void testIsAdmin_UserNotFound() {
        String email = "nonexistent@test.com";

        when(userRepository.findUserByEmailIgnoreCase(email)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> checkRoleUserService.isAdmin(email));
    }

    @Test
    void testIsUserOrAdmin_UserIsOwner() {
        String email = "testuser@test.com";
        User owner = new User();
        owner.setEmail(email);

        boolean isUserOrAdminResult = checkRoleUserService.isUserOrAdmin(email, owner);

        assertTrue(isUserOrAdminResult);
    }

    @Test
    void testIsUserOrAdmin_UserIsAdmin() {
        String email = "admin@test.com";
        User adminUser = new User();
        adminUser.setEmail(email);
        adminUser.setRole(Role.ADMIN);

        when(userRepository.findUserByEmailIgnoreCase(email)).thenReturn(Optional.of(adminUser));

        boolean isUserOrAdminResult = checkRoleUserService.isUserOrAdmin(email, new User());

        assertTrue(isUserOrAdminResult);
    }

    @Test
    void testIsUserOrAdmin_UserIsNeitherOwnerNorAdmin() {
        String email = "user@test.com";
        User regularUser = new User();
        regularUser.setEmail(email);
        regularUser.setRole(Role.USER);

        when(userRepository.findUserByEmailIgnoreCase(email)).thenReturn(Optional.of(regularUser));

        boolean isUserOrAdminResult = checkRoleUserService.isUserOrAdmin(email, new User());

        assertFalse(isUserOrAdminResult);
    }

    @Test
    void testIsUserOrAdmin_UserNotFound() {
        String email = "nonexistent@test.com";

        when(userRepository.findUserByEmailIgnoreCase(email)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> checkRoleUserService.isUserOrAdmin(email, new User()));
    }
}
