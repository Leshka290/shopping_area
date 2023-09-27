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

    boolean setPassword(NewPasswordDto newPasswordDto, Authentication auth);

    UserDto getUser(Authentication auth);

    UpdateUserDto updateUser(UpdateUserDto userDto, Authentication auth);

    void updateUserImage(MultipartFile file, Authentication auth) throws IOException;

}
