package com.skyteam.shopping_area.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Класс пользователя со свойствами:
 * <p>id</p><p>firstName</p><p>lastName</p><p>email</p><p>phone</p><p>role</p><p>image</p>
 *
 * @author leshka290
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "User")
public class UserDto {

    @Schema(description = "id пользователя")
    private int id;
    @Schema(description = "имя пользователя")
    private String firstName;
    @Schema(description = "фамилия пользователя")
    private String lastName;
    @Schema(description = "логин пользователя")
    private String email;
    @Schema(description = "телефон пользователя")
    private String phone;
    @Schema(description = "роль пользователя")
    private Role role;
    @Schema(description = "ссылка на аватар пользователя")
    private String image;
}
