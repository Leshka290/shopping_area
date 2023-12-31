package com.skyteam.shopping_area.model;

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
    @GeneratedValue
    private int id;
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ad ads;

    private LocalDateTime createdAt;
}
