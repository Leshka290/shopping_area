package com.skyteam.shopping_area.services.impl;

import com.skyteam.shopping_area.dto.NewPasswordDto;
import com.skyteam.shopping_area.dto.UserDto;
import com.skyteam.shopping_area.exceptions.InvalidPasswordException;
import com.skyteam.shopping_area.models.Image;
import com.skyteam.shopping_area.models.User;
import com.skyteam.shopping_area.repositories.UserRepository;
import com.skyteam.shopping_area.services.ImageService;
import com.skyteam.shopping_area.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Реализует CRUD операции с пользователями класса User
 *
 *  @author leshka290
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    Logger log = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final ImageService imageService;
    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ImageService imageService, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.imageService = imageService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean setPassword(NewPasswordDto newPasswordDto) {
        User user = findAuthUser();

        if (!checkPassword(user, newPasswordDto.getCurrentPassword())) {
            throw new InvalidPasswordException("Current password is not valid");
        }
        changeUserPassword(user, newPasswordDto.getNewPassword());
        log.info("Password " + user.getUsername() + " updated");
        return true;
    }

    @Override
    public boolean checkPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public UserDto getUser(Authentication auth) {
        User user = userRepository.findUserByUsername(auth.getName()).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exist"));
        UserDto userDto = modelMapper.map(user, UserDto.class);

        return userDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        userRepository.save(modelMapper.map(userDto, User.class));
        return userDto;
    }

    @Override
    public void updateUserImage(MultipartFile file) throws IOException {
//        log.info("New avatar {}", file.getName());
        User user = findAuthUser();
        Image newImage;

        if (userRepository.findUserByUsername(user.getUsername()).get().getImage() == null) {
            newImage = imageService.uploadImage(user.getId(), file);
        } else {
            newImage = imageService.uploadImage(user.getId(), file);
        }
        user.setImage(newImage.getId());
        userRepository.save(user);
    }

    @Override
    public User findAuthUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String username;

        if (user != null) {
            username = ((UserDetails) user).getUsername();
        } else {
            username = user.toString();
        }
        return userRepository.findUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exist"));
    }
}
