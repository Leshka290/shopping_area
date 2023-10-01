package com.skyteam.shopping_area.service;
import com.skyteam.shopping_area.dto.RegisterDto;
import com.skyteam.shopping_area.exception.IncorrectUsernameException;
import com.skyteam.shopping_area.model.User;
import com.skyteam.shopping_area.repository.UserRepository;
import com.skyteam.shopping_area.service.impl.UserDetailsManagerCustom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDetailsManagerCustomTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserDetailsManagerCustom userDetailsManagerCustom;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername_UserFound() {
        String username = "testuser@test.ru";
        User user = new User();
        user.setEmail(username);
        user.setPassword("testPassword");
        when(userRepository.findUserByEmailIgnoreCase(username)).thenReturn(Optional.of(user));

        UserDetails userDetails = userDetailsManagerCustom.loadUserByUsername(username);

        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        String username = "test@test.ru";
        when(userRepository.findUserByEmailIgnoreCase(username)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userDetailsManagerCustom.loadUserByUsername(username));
    }

    @Test
    void testCreateUser_UserDoesNotExist() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("test@test.ru");
        registerDto.setPassword("testPassword");
        registerDto.setFirstName("Dima");
        registerDto.setLastName("SkyPro");
        registerDto.setPhone("123456789");

        when(userRepository.findUserByEmailIgnoreCase(registerDto.getUsername())).thenReturn(Optional.empty());

        userDetailsManagerCustom.createUser(registerDto);

        verify(encoder).encode("testPassword");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testCreateUser_UserExists() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("test@test.ru");
        registerDto.setPassword("password");
        registerDto.setFirstName("Dima");
        registerDto.setLastName("SkyPro");
        registerDto.setPhone("123456789");

        when(userRepository.findUserByEmailIgnoreCase(registerDto.getUsername())).thenReturn(Optional.of(new User()));

        assertThrows(IncorrectUsernameException.class, () -> userDetailsManagerCustom.createUser(registerDto));
    }
}
