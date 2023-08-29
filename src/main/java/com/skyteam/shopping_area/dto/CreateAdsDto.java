package com.skyteam.shopping_area.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdsDto {

    private String description;
    private int price;
    private String title;
}
