package com.skyteam.shopping_area.services;

import com.skyteam.shopping_area.dto.NewPasswordDto;
import com.skyteam.shopping_area.dto.UserDto;
import com.skyteam.shopping_area.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Методы для работы с классом User
 *
 * @author leshka290
 */
public interface UserService {

    boolean setPassword(NewPasswordDto newPasswordDto);

    boolean checkPassword(User user, String oldPassword);

    void changeUserPassword(User user, String password);

    UserDto getUser(Authentication auth);

    UserDto updateUser(UserDto userDto);

    void updateUserImage(MultipartFile file) throws IOException;

    User findAuthUser();
}
