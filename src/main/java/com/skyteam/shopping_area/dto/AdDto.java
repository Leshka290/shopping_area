package com.skyteam.shopping_area.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс объявление со свойствами:
 * <p>id</p><p>author</p><p>image</p><p>pk</p><p>price</p><p>title</p>
 *
 * @author leshka290
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Ad")
public class AdDto {

    @Schema(description = "id автора объявления")
    private int author;
    @Schema(description = "ссылка на картинку объявления")
    private String image;
    @Schema(description = "id объявления")
    private long pk;
    @Schema(description = "цена объявления")
    private int price;
    @Schema(description = "заголовок объявления")
    private String title;

}
