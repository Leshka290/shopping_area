package com.skyteam.shopping_area.exceptions;

/**
 * Ошибка возникающая если введен не правильный пароль
 *
 * @author leshka290
 */
public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String message) {
        super(message);
    }
}
