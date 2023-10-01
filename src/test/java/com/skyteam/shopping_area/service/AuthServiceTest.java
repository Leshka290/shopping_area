package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.dto.RegisterDto;
import com.skyteam.shopping_area.service.AuthService;
import com.skyteam.shopping_area.service.impl.AuthServiceImpl;
import com.skyteam.shopping_area.service.impl.UserDetailsManagerCustom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @Mock
    private UserDetailsManagerCustom userDetailsManagerCustom;

    @Mock
    private PasswordEncoder passwordEncoder;

    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authService = new AuthServiceImpl(userDetailsManagerCustom, passwordEncoder);
    }

    @Test
    void testLogin_SuccessfulLogin() {
        String username = "testuser";
        String password = "password";
        String encodedPassword = "encoded_password";

        when(userDetailsManagerCustom.loadUserByUsername(username))
                .thenReturn(User.withUsername(username)
                        .password(encodedPassword)
                        .authorities("ROLE_USER")
                        .build());
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);

        boolean loginResult = authService.login(username, password);

        assertTrue(loginResult);
        verify(userDetailsManagerCustom, times(1)).loadUserByUsername(username);
        verify(passwordEncoder, times(1)).matches(password, encodedPassword);
    }

    @Test
    void testLogin_IncorrectPassword() {
        String username = "testuser";
        String password = "password";
        String encodedPassword = "encoded_password";

        when(userDetailsManagerCustom.loadUserByUsername(username))
                .thenReturn(User.withUsername(username)
                        .password(encodedPassword)
                        .authorities("ROLE_USER")
                        .build());
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(false);

        boolean loginResult = authService.login(username, password);

        assertFalse(loginResult);
        verify(userDetailsManagerCustom, times(1)).loadUserByUsername(username);
        verify(passwordEncoder, times(1)).matches(password, encodedPassword);
    }

    @Test
    void testRegister_SuccessfulRegistration() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("testuser");
        registerDto.setPassword("password");

        boolean registrationResult = authService.register(registerDto);

        assertTrue(registrationResult);
        verify(userDetailsManagerCustom, times(1)).createUser(registerDto);
    }
}
