package com.skyteam.shopping_area.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс создания и обновления комментария со свойствами:
 * <p>text</p>
 *
 * @author leshka290
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CreateOrUpdateComment")
public class CreateOrUpdateCommentDto {

    @Schema(description = "текст комментария", minLength = 8, maxLength = 64)
    private String text;
}
