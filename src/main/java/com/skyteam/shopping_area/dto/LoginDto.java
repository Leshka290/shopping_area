package com.skyteam.shopping_area.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Login")
public class LoginDto {

    @Schema(description = "логин", minLength = 4, maxLength = 32)
    private String username;
    @Schema(description = "пароль", minLength = 8, maxLength = 16)
    private String password;
}
