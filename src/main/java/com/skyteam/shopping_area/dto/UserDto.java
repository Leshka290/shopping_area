package com.skyteam.shopping_area.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Класс пользователя со свойствами:
 * <p>id</p><p>firstName</p><p>lastName</p><p>email</p><p>phone</p><p>city</p><p>image</p><p>regDate</p>
 *
 * @author leshka290
 */
@Data
@Schema(description = "Пользователь")
public class UserDto {

    @Schema(description = "Идентификатор", accessMode = Schema.AccessMode.READ_ONLY)
    @Id
    private Long id;

    @Schema(description = "ФИО", example = "Иван")
    private String firstName;
    @Schema(description = "ФИО", example = "Иванов")
    private String lastName;
    @Schema(description = "ФИО", example = "email@mail.ru")
    private String email;
    @Schema(description = "ФИО")
    private String phone;
    @Schema(description = "ФИО")
    private String city;
    @Schema(description = "Изображение")
    private String image;
    @Schema(description = "Дата регистрации")
    private LocalDateTime regDate;
}
