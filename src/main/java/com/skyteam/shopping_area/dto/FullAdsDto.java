package com.skyteam.shopping_area.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

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
public class FullAdsDto {

    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String image;
    private String phone;
    private int pk;
    private int price;
    private String title;
}
