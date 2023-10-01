package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.dto.RegisterDto;
import com.skyteam.shopping_area.dto.ResponseWrapperAdsDto;

/**
 * Интерфейс для работы с авторизацией
 *
 * @author youcanwakemeup
 */
public interface AuthService {

    /**
     * Авторизация пользователя
     *
     * @param userName e-mail (логин) пользователя
     * @param password пароль пользователя
     * @return <code>true</code> авторизирован, <code>false</code> в случае неудачи
     */
    boolean login(String userName, String password);

    /**
     * Регистрация пользователя
     *
     * @param register данные пользователя
     * @return <code>true</code> зарегистрирован, <code>false</code> в случае неудачи
     */
    boolean register(RegisterDto register);
}
