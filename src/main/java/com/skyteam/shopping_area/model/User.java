package com.skyteam.shopping_area.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skyteam.shopping_area.dto.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Класс пользователя для БД со свойствами:
 * <p>id</p><p>firstName</p><p>lastName</p><p>username</p><p>email</p><p>password</p>
 * <p>phone</p><p>role</p><p>image</p>
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
    private int id;

    private String email;

    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(fetch = FetchType.EAGER)
    private Image image;

    public User(int id, String email, String password,
                Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
