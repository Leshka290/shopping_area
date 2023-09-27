package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.model.User;

public interface CheckRoleUserService {

    /**
     * Проверяет, является ли пользователь владельцем объявления / комментария
     *
     * @param email e-mail пользователя
     * @param owner владелец объявления / комментария
     */
    boolean isUser(String email, User owner);

    /**
     * Проверяет, является ли пользователь администратором
     *
     * @param email e-mail администратора
     */
    boolean isAdmin(String email);

    /**
     * Проверяет, является ли пользователь владельцем объявления / комментария или администратором
     *
     * @param email e-mail пользователя / администратора
     * @param owner владелец объявления / комментария
     */
    boolean isUserOrAdmin(String email, User owner);
}
