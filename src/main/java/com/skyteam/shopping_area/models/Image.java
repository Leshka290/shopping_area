package com.skyteam.shopping_area.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Класс изображения пользователя для БД со свойствами:
 * <p>id</p><p>image</p>
 *
 * @author leshka290
 */
@Getter
@Setter
@Entity
public class Image {

    @Id
    private String id;
    private byte[] image;
}
