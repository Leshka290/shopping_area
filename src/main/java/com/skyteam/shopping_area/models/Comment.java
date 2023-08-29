package com.skyteam.shopping_area.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Класс объявлений для БД со свойствами:
 * <p>id</p><p>text</p><p>author</p><p>ads</p><p>createdAt</p>
 *
 * @author leshka290
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    private long id;
    private String text;

    @ManyToOne
    private User author;

    @ManyToOne
    private Ads ads;

    private LocalDateTime createdAt;
}
