package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.dto.CommentDto;
import com.skyteam.shopping_area.dto.ResponseWrapperCommentDto;

public interface CommentService {

    CommentDto addComment(String ad_pk, CommentDto commentDto);

    ResponseWrapperCommentDto getComments(String ad_pk);

    boolean deleteComments(String ad_pk, int id);

    CommentDto updateComments(String ad_pk, int id, CommentDto commentDto);
}
