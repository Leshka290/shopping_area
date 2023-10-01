package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.dto.NewPasswordDto;
import com.skyteam.shopping_area.dto.UpdateUserDto;
import com.skyteam.shopping_area.dto.UserDto;
import com.skyteam.shopping_area.exception.InvalidPasswordException;
import com.skyteam.shopping_area.mapper.UserMapper;
import com.skyteam.shopping_area.model.Image;
import com.skyteam.shopping_area.model.User;
import com.skyteam.shopping_area.repository.UserRepository;
import com.skyteam.shopping_area.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ImageService imageService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }







    @Test
    public void testGetUser_UserNotFound() {
        Authentication auth = mock(Authentication.class);

        when(userRepository.findUserByEmailIgnoreCase(anyString())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.getUser(auth));
    }



    @Test
    public void testUpdateUser_ImageUpdate() throws IOException {
        Authentication auth = mock(Authentication.class);
        User user = new User();
        user.setEmail("test@test.ru");
        MultipartFile file = mock(MultipartFile.class);
        Image newImage = new Image();
        newImage.setName("avatar.jpg");

        when(userRepository.findUserByEmail(auth.getName())).thenReturn(Optional.of(user));
        when(imageService.saveImageFile(file)).thenReturn(newImage);

        userService.updateUserImage(file, auth);

        assertEquals(newImage, user.getImage());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUserImage_IOError() throws IOException {
        Authentication auth = mock(Authentication.class);
        User user = new User();
        user.setEmail("test@test.ru");
        MultipartFile file = mock(MultipartFile.class);

        when(userRepository.findUserByEmail(auth.getName())).thenReturn(Optional.of(user));
        when(imageService.saveImageFile(file)).thenThrow(IOException.class);

        assertThrows(RuntimeException.class, () -> userService.updateUserImage(file, auth));
    }
}
