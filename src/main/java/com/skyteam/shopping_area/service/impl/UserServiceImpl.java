package com.skyteam.shopping_area.service.impl;

import com.skyteam.shopping_area.dto.NewPasswordDto;
import com.skyteam.shopping_area.dto.UpdateUserDto;
import com.skyteam.shopping_area.dto.UserDto;
import com.skyteam.shopping_area.exception.InvalidPasswordException;
import com.skyteam.shopping_area.exception.UserNotFoundException;
import com.skyteam.shopping_area.mapper.UserMapper;
import com.skyteam.shopping_area.model.Image;
import com.skyteam.shopping_area.model.User;
import com.skyteam.shopping_area.repository.UserRepository;
import com.skyteam.shopping_area.service.ImageService;
import com.skyteam.shopping_area.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Реализует CRUD операции с пользователями класса User
 *
 * @author leshka290
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ImageService imageService;
    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;
    private final UserMapper userMapper;

    @Override
    public boolean setPassword(NewPasswordDto newPasswordDto, Authentication auth) {
        User user = userRepository
                .findUserByEmailIgnoreCase(auth.getName())
                .orElseThrow(UserNotFoundException::new);

        if (!checkPassword(user, newPasswordDto.getCurrentPassword())) {
            throw new InvalidPasswordException("Current password is not valid");
        }
        changeUserPassword(user, newPasswordDto.getNewPassword());
        log.info("Password " + user.getUsername() + " updated");
        return true;
    }

    private boolean checkPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    private void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public UserDto getUser(Authentication auth) {
        User user = userRepository.findUserByEmailIgnoreCase(auth.getName()).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exist"));

        return userMapper.userToUserDto(user);
    }

    @Override
    public UpdateUserDto updateUser(UpdateUserDto userDto, Authentication auth) {
        User user = userRepository
                .findUserByEmail(auth.getName())
                .orElseThrow(UserNotFoundException::new);

        log.info(user.getUsername());
        if (userDto.getFirstName() != null && !userDto.getFirstName().isBlank()) {
            user.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null && !userDto.getLastName().isBlank()) {
            user.setLastName(userDto.getLastName());
        }
        if (userDto.getPhone() != null && !userDto.getPhone().isBlank()) {
            user.setPhone(userDto.getPhone());
        }
        log.info(user.getUsername());
        return modelMapper.map(userRepository.save(user), UpdateUserDto.class);
    }

    @Override
    public void updateUserImage(MultipartFile file, Authentication auth) {
        log.info("New avatar {}", file.getName());
        User user = userRepository.findUserByEmail(auth.getName())
                .orElseThrow(UserNotFoundException::new);
        Image newImage;

        try {
            newImage = imageService.saveImageFile(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        user.setImage(newImage);
        userRepository.save(user);
    }
}
