package com.skyteam.shopping_area.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Comments")
public class ResponseWrapperCommentDto {

    @Schema(description = "общее количество комментариев")
    private int count;
    private Collection<CommentDto> results;
}
