package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.model.Image;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Интерфейс для работы с картинками
 *
 * @author leshka290
 */
public interface ImageService {

    /**
     * Переводит изображение в ответ
     *
     * @param imageId {@link Integer} идентификатор картинки
     * @param response {@link HttpServletResponse} ответ
     * @throws IOException возникаемое исключение
     */
    void transferImageToResponse(Integer imageId, HttpServletResponse response) throws IOException;

    /**
     * Сохраняет картинку
     *
     * @param imageFile {@link MultipartFile} файл картинки
     * @return объект {@link Image}
     * @throws IOException возникаемое исключение
     */
    Image saveImageFile(MultipartFile imageFile) throws IOException;
}
