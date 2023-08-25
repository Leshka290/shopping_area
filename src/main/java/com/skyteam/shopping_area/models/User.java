package com.skyteam.shopping_area.models;

import lombok.Data;

import javax.persistence.*;

/**
 * Класс пользователя для БД со свойствами:
 * <p>id</p><p>firstName</p><p>lastName</p>
 *
 * @author leshka290
 */
@Entity
@Data
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
}
