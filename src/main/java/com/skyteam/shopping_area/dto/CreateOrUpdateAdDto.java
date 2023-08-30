package com.skyteam.shopping_area.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс создания и обновления объявления со свойствами:
 * <p>description</p><p>price</p><p>title</p>
 *
 * @author leshka290
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CreateOrUpdateAd")
public class CreateOrUpdateAdDto {

    @Schema(description = "описание объявления", minLength = 8, maxLength = 64)
    private String description;
    @Schema(description = "цена объявления", minimum = "0", maximum = "10000000")
    private int price;
    @Schema(description = "заголовок объявления", minLength = 4, maxLength = 32)
    private String title;
}
