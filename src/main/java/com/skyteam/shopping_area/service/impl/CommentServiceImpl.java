package com.skyteam.shopping_area.service.impl;

import com.skyteam.shopping_area.dto.CommentDto;
import com.skyteam.shopping_area.dto.ResponseWrapperCommentDto;
import com.skyteam.shopping_area.exception.CommentNotFoundException;
import com.skyteam.shopping_area.model.Comment;
import com.skyteam.shopping_area.model.User;
import com.skyteam.shopping_area.repository.AdsRepository;
import com.skyteam.shopping_area.repository.CommentRepository;
import com.skyteam.shopping_area.repository.UserRepository;
import com.skyteam.shopping_area.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Реализует CRUD операции класса Comment
 *
 * @author leshka290
 */
@Service
@Slf4j
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AdsRepository adsRepository;
    private final ModelMapper modelMapper;

    @Override
    public CommentDto addComment(int id, CommentDto commentDto) {
        User user = userRepository.findUserByEmail(SecurityContextHolder.getContext()
                .getAuthentication()
                .getName()).orElseThrow();

        Comment comment = new Comment();
        comment.setAuthor(user);
        comment.setAds(adsRepository.findById(id).orElseThrow());
        comment.setText(commentDto.getText());
        return modelMapper.map(commentRepository.save(comment), CommentDto.class);
    }

    @Override
    public ResponseWrapperCommentDto getComments(int id) {

        List<Comment> commentList = commentRepository.findAllByAdsId(id);
        return modelMapper.map(commentList, ResponseWrapperCommentDto.class);
    }

    @Override
    public boolean deleteComments(int adId, int commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        commentRepository.delete(comment);
        commentRepository.deleteAdsComment(adId, commentId);
        return !commentRepository.existsById(comment.getId());
    }

    @Override
    public CommentDto updateComments(int adId, int commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        comment.setText(comment.getText());
        adsRepository.save(comment.getAds());

        return modelMapper.map(commentRepository.save(comment), CommentDto.class);
    }
}
