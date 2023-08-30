package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.dto.CommentDto;
import com.skyteam.shopping_area.dto.ResponseWrapperCommentDto;

public interface CommentService {

    CommentDto addComment(int id, CommentDto commentDto);

    ResponseWrapperCommentDto getComments(int id);

    boolean deleteComments(int adId, int commentId);

    CommentDto updateComments(int adId, int commentId, CommentDto commentDto);

}
