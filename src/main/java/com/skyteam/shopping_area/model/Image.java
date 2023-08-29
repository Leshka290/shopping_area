package com.skyteam.shopping_area.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Класс изображения пользователя для БД со свойствами:
 * <p>id</p><p>image</p>
 *
 * @author leshka290
 */
@Getter
@Setter
@Entity
@Table(name = "images")
public class Image {

    @Id
    private String id;
    private byte[] image;
}
