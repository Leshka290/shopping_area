package com.skyteam.shopping_area.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Класс объявлений для БД со свойствами:
 * <p>id</p><p>author</p><p>image</p><p>price</p><p>title</p><p>description</p><p>comments</p>
 *
 * @author leshka290
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ads")
public class Ad {

    @Transient
    public static final String SEQUENCE_NAME = "ads_sequence";

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User author;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Image image;
    @PositiveOrZero
    private int price;
    private String title;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Collection<Comment> comments;
}