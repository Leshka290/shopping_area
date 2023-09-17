package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.dto.NewPasswordDto;
import com.skyteam.shopping_area.dto.UpdateUserDto;
import com.skyteam.shopping_area.dto.UserDto;
import com.skyteam.shopping_area.model.User;
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

    UpdateUserDto updateUser(UpdateUserDto userDto);

    void updateUserImage(MultipartFile file) throws IOException;

    User findAuthUser();
}
