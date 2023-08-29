package com.skyteam.shopping_area.dto;

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
public class AdsDto {

    private long author;
    private String image;
    private long pk;
    private int price;
    private String title;
}
