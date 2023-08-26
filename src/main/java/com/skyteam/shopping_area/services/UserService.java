package com.skyteam.shopping_area.services;

import com.skyteam.shopping_area.dto.NewPasswordDto;
import com.skyteam.shopping_area.dto.UserDto;
import com.skyteam.shopping_area.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

/**
 * Методы для работы с классом User
 *
 * @author leshka290
 */
public interface UserService {

    boolean setPassword(NewPasswordDto newPasswordDto);

    UserDto getUser(Authentication auth);

    UserDto updateUser(UserDto userDto);

    void updateUserImage(MultipartFile file);

    User findAuthUser();
}
