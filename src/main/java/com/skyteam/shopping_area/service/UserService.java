package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.dto.NewPasswordDto;
import com.skyteam.shopping_area.dto.UpdateUserDto;
import com.skyteam.shopping_area.dto.UserDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Интерфейс для работы с классом User
 *
 * @author leshka290
 */
public interface UserService {

    /**
     * Изменение пароля зарегистрированного пользователя
     *
     * @param newPasswordDto {@link NewPasswordDto} объект, содержащий текущий и новый пароли
     * @param auth           {@link Authentication} данные о текущем пользователе
     * @return <code>true</code> если пароль изменен, <code>false</code> в случае неудачи
     */
    boolean setPassword(NewPasswordDto newPasswordDto, Authentication auth);

    /**
     * Получение информации об зарегистрированном пользователе
     *
     * @param auth {@link Authentication} данные о текущем пользователе
     * @return Объект {@link UserDto}
     */
    UserDto getUser(Authentication auth);

    /**
     * Изменение информации зарегистрированного пользователя
     *
     * @param userDto новая информация об пользователе
     * @param auth    {@link Authentication} данные о текущем пользователе
     * @return {@link UserDto} обновленные данные, в случае успешного изменения
     */
    UpdateUserDto updateUser(UpdateUserDto userDto, Authentication auth);

    /**
     * Изменяет изображение аватарки зарегистрированного полдьзователя
     *
     * @param file {@link MultipartFile} файл картинки
     * @param auth {@link Authentication} данные о текущем пользователе
     * @throws IOException возникаемое исключение
     */
    void updateUserImage(MultipartFile file, Authentication auth) throws IOException;

}
