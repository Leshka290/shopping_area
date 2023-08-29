package com.skyteam.shopping_area.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
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
public class Ads {

    @Transient
    public static final String SEQUENCE_NAME = "ads_sequence";

    @Id
    private long id;

    @ManyToOne
    private User author;

    @ManyToOne
    private Image image;
    private int price;
    private String title;
    private String description;

    @OneToMany
    private Collection<Comment> comments = new ArrayList<>();
}