package com.skyteam.shopping_area.controller;

import com.skyteam.shopping_area.dto.NewPasswordDto;
import com.skyteam.shopping_area.dto.UpdateUserDto;
import com.skyteam.shopping_area.dto.UserDto;
import com.skyteam.shopping_area.service.ImageService;
import com.skyteam.shopping_area.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * Контроллер для работы с пользователями
 *
 * @author leshka290
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ImageService imageService;

    @Operation(summary = "Обновление пароля")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPasswordDto newPasswordDto, Authentication auth) {
        log.info("Request update password");

        return ResponseEntity.ok().body(userService.setPassword(newPasswordDto, auth));
    }

    @Operation(summary = "Получение информации об авторизованном пользователе")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser(Authentication auth) {
        log.info("Request GET info about authorize user");
        return ResponseEntity.ok(userService.getUser(auth));
    }

    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UpdateUserDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PatchMapping("/me")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDto userDto, Authentication auth) {
        log.info("Update info user");
        userService.updateUser(userDto, auth);
        return ResponseEntity.ok().body(userDto);
    }

    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ApiResponse(responseCode = "204", description = "No Content")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserImage(@RequestBody MultipartFile image, Authentication auth) throws IOException {
        log.info("Update image by user");
        userService.updateUserImage(image, auth);
        return ResponseEntity.ok().build();
    }

//    @Operation(summary = "Получение аватара")
//    @ApiResponse(responseCode = "200", description = "OK")
//    @ApiResponse(responseCode = "404", description = "Not found", content = @Content())
//    @GetMapping(value = "/users/image/{id}", produces = {
//            MediaType.IMAGE_PNG_VALUE,
//            MediaType.IMAGE_JPEG_VALUE,
//            MediaType.APPLICATION_OCTET_STREAM_VALUE,
//            MediaType.IMAGE_GIF_VALUE
//    })
//    public ResponseEntity<?> getImage(@PathVariable("id") Integer id) {
//        return ResponseEntity.ok(imageService.getImage(id));
//    }
}
