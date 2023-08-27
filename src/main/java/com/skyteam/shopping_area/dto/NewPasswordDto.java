package com.skyteam.shopping_area.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Класс DTO для нового пароля
 * <p>currentPassword</p><p>newPassword</p>
 *
 * @author leshka290
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPasswordDto {

    private String currentPassword;

    @NotBlank
    private String newPassword;
}
