package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.dto.CommentDto;
import com.skyteam.shopping_area.dto.ResponseWrapperAdsDto;
import com.skyteam.shopping_area.dto.ResponseWrapperCommentDto;
import org.springframework.security.core.Authentication;

/**
 * Интерфейс для работы с комментариями
 *
 * @author leshka290
 */
public interface CommentService {

    /**
     * Добавляет комментарий
     *
     * @param id         идентификатор объявления
     * @param commentDto комментарий
     * @return объект {@link CommentDto}
     */
    CommentDto addComment(int id, CommentDto commentDto);

    /**
     * Получает комментарий по id
     *
     * @param id идентификатор объявления
     * @return объект {@link ResponseWrapperCommentDto}
     */
    ResponseWrapperCommentDto getComments(int id);

    /**
     * Удаляет комментарий
     *
     * @param adId      идентификатор объявления
     * @param commentId идентификатор комментария
     * @param auth      данные пользователя
     * @return <code>true</code> если комментарий удален, <code>false</code> в случае неудачи
     */
    boolean deleteComments(int adId, int commentId, Authentication auth);

    /**
     * Обновляет комментарий
     *
     * @param adId       идентификатор объявления
     * @param commentId  идентификатор комментария
     * @param commentDto новый комментарий
     * @param auth       данные пользователя
     * @return объект {@link CommentDto}
     */
    CommentDto updateComments(int adId, int commentId, CommentDto commentDto, Authentication auth);

}
