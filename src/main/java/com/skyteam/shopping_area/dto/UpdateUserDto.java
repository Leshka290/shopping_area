package com.skyteam.shopping_area.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс объявление пользователя со свойствами:
 * <p>firstName</p><p>lastName</p><p>phone</p>
 *
 * @author leshka290
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "UpdateUser")
public class UpdateUserDto {

    @Schema(description = "имя пользователя", maxLength = 10, minLength = 3)
    private String firstName;
    @Schema(description = "фамилия пользователя", maxLength = 10, minLength = 3)
    private String lastName;
    @Schema(description = "телефон пользователя", pattern = "\\+7\\s?\\d{3}\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
}
