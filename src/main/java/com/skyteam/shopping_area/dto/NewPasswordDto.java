package com.skyteam.shopping_area.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
@Schema(name = "NewPassword")
public class NewPasswordDto {

    @Schema(description = "текущий пароль", minLength = 8, maxLength = 16)
    private String currentPassword;

    @NotBlank
    @Schema(description = "новый пароль", minLength = 8, maxLength = 16)
    private String newPassword;
}
