package com.skyteam.shopping_area.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skyteam.shopping_area.constants.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Класс пользователя для БД со свойствами:
 * <p>id</p><p>firstName</p><p>lastName</p>
 *
 * @author leshka290
 */
@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;

    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private String phone;

    private Role role;
    private String image;

    public User(long id, String username, String email, String password,
                Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
