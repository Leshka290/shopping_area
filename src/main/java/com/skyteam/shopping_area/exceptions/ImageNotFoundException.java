package com.skyteam.shopping_area.exceptions;

/**
 * Ошибка возникающая если Image не найден
 */
public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(String message) {
        super(message);
    }
}
