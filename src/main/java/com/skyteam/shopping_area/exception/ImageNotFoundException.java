package com.skyteam.shopping_area.exception;

/**
 * Ошибка возникающая если Image не найден
 */
public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(String message) {
        super(message);
    }

    public ImageNotFoundException() {
    }
}
