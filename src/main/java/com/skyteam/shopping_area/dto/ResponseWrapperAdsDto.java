package com.skyteam.shopping_area.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Класс контейнер объявления со свойствами:
 * <p>count</p><p>result</p>
 *
 * @author leshka290
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Ads")
public class ResponseWrapperAdsDto {

    @Schema(description = "общее количество объявлений")
    private int count;
    private List<AdDto> results;
}
