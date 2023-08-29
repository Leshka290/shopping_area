package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.dto.CommentDto;
import com.skyteam.shopping_area.dto.ResponseWrapperCommentDto;

public interface CommentService {

    CommentDto addComment(String adPk, CommentDto commentDto);

    ResponseWrapperCommentDto getComments(String adPk);

    boolean deleteComments(String adPk, int id);

    CommentDto updateComments(String adPk, int id, CommentDto commentDto);
}
