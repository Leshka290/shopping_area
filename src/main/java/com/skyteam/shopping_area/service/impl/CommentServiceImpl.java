package com.skyteam.shopping_area.service.impl;

import com.skyteam.shopping_area.dto.CommentDto;
import com.skyteam.shopping_area.dto.ResponseWrapperCommentDto;
import com.skyteam.shopping_area.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Реализует CRUD операции класса Comment
 *
 * @author leshka290
 */
@Service
@Slf4j
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Override
    public CommentDto addComment(int id, CommentDto commentDto) {

        return null;
    }

    @Override
    public ResponseWrapperCommentDto getComments(int id) {
        //реализовать метод
        return null;
    }

    @Override
    public boolean deleteComments(int adId, int commentId) {
        //реализовать метод
        return false;
    }

    @Override
    public CommentDto updateComments(int adId, int commentId, CommentDto commentDto) {
        //реализовать метод
        return null;
    }
}
