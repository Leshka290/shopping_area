package com.skyteam.shopping_area.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * Класс контейнер объявления со свойствами:
 * <p>count</p><p>result</p>
 *
 * @author leshka290
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapperAdsDto {

    private int count;
    private Collection<AdsDto> results;
}
