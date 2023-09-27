package com.skyteam.shopping_area.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс комметарий со свойствами:
 * <p>author</p><p>authorImage</p><p>authorFirstName</p><p>createdAt</p><p>pk</p><p>text</p>
 *
 * @author leshka290
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Comment")
public class CommentDto {

    @Schema(description = "id автора объявления")
    private int author;
    @Schema(description = "ссылка на аватар автора комментария")
    private String authorImage;
    @Schema(description = "имя создателя комментария")
    private String authorFirstName;
    @Schema(description = "дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970")
    private String createdAt;
    @Schema(description = "id комментария")
    private int pk;
    @Schema(description = "текст комментария")
    private String text;
}
