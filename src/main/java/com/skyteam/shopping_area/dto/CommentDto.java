package com.skyteam.shopping_area.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Класс комметарий со свойствами:
 * <p>author</p><p>createdAt</p><p>pk</p><p>text</p>
 *
 * @author leshka290
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private long author;
    private String createdAt;
    private long pk;
    private String text;
}
