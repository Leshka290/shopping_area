package com.skyteam.shopping_area.service.impl;

import com.skyteam.shopping_area.dto.CommentDto;
import com.skyteam.shopping_area.dto.ResponseWrapperCommentDto;
import com.skyteam.shopping_area.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Override
    public CommentDto addComment(String ad_pk, CommentDto commentDto) {
        //реализовать метод
        return null;
    }

    @Override
    public ResponseWrapperCommentDto getComments(String ad_pk) {
        //реализовать метод
        return null;
    }

    @Override
    public boolean deleteComments(String ad_pk, int id) {
        //реализовать метод
        return false;
    }

    @Override
    public CommentDto updateComments(String ad_pk, int id, CommentDto commentDto) {
        //реализовать метод
        return null;
    }
}
