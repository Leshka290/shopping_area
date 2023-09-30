package com.skyteam.shopping_area.exception;

/**
 * Ошибка возникающая если объявление не найдено
 */
public class AdNotFoundException extends RuntimeException {
    public AdNotFoundException() {
    }

    public AdNotFoundException(String s) {
        super(s);
    }
}
