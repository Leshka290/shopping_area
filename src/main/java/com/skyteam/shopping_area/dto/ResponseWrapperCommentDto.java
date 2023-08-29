package com.skyteam.shopping_area.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * Класс контейнер комментария со свойствами:
 * <p>count</p><p>results</p>
 *
 * @author leshka290
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapperCommentDto {

    private int count;
    private Collection<CommentDto> results;
}
