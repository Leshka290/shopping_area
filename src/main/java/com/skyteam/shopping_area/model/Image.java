package com.skyteam.shopping_area.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.nio.file.Path;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private byte[] image;
    private String name;
    private String contentType;

    private long size;
    private String filePath;
    private String mediaType;
    private String fileExtension;

    public Path getPath() {
        return Path.of(this.filePath);
    }

    public String getUrl() {
        return String.format("/%s/%s", getPath().getParent(), getId());
    }
}
