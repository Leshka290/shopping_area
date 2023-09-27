package com.skyteam.shopping_area.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс с полным объявлением со свойствами:
 * <p>authorFirstName</p><p>authorLastName</p><p>description</p><p>email</p>
 * <p>image</p><p>phone</p><p>pk</p><p>price</p><p>title</p>
 *
 * @author leshka290
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "ExtendedAd")
public class ExtendedAdDto {

    @Schema(description = "имя автора объявления")
    private String authorFirstName;
    @Schema(description = "фамилия автора объявления")
    private String authorLastName;
    @Schema(description = "описание объявления")
    private String description;
    @Schema(description = "логин автора объявления")
    private String email;
    @Schema(description = "ссылка на картинку объявления")
    private String image;
    @Schema(description = "телефон автора объявления")
    private String phone;
    @Schema(description = "id объявления")
    private int pk;
    @Schema(description = "цена объявления")
    private int price;
    @Schema(description = "заголовок объявления")
    private String title;
}
