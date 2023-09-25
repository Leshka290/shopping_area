package com.skyteam.shopping_area.service.impl;

import com.skyteam.shopping_area.dto.CommentDto;
import com.skyteam.shopping_area.dto.ResponseWrapperCommentDto;
import com.skyteam.shopping_area.exception.AdNotFoundException;
import com.skyteam.shopping_area.exception.CommentNotFoundException;
import com.skyteam.shopping_area.mapper.CommentMapper;
import com.skyteam.shopping_area.model.Comment;
import com.skyteam.shopping_area.model.User;
import com.skyteam.shopping_area.repository.AdsRepository;
import com.skyteam.shopping_area.repository.CommentRepository;
import com.skyteam.shopping_area.repository.UserRepository;
import com.skyteam.shopping_area.service.CheckRoleUserService;
import com.skyteam.shopping_area.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private final CheckRoleUserService checkRoleUserService;
    private final CommentMapper commentMapper;

    @Override
    public CommentDto addComment(int id, CommentDto commentDto) {

        User user = userRepository.findUserByEmailIgnoreCase(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName()).orElseThrow();

        Comment comment = new Comment();
        comment.setAuthor(user);
        comment.setAds(adsRepository.findById(id).orElseThrow());
        comment.setText(commentDto.getText());
        comment.setCreatedAt(LocalDateTime.now());
        return commentMapper.commentToCommentDto(commentRepository.save(comment));
    }

    @Override
    public ResponseWrapperCommentDto getComments(int id) {

        List<Comment> commentList = commentRepository.findAllByAdsId(id);
        return commentMapper.listCommentToCommentDto(commentList.size(), commentList);
    }

    @Override
    public boolean deleteComments(int adId, int commentId, Authentication auth) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);
        User commentOwner = comment.getAuthor();
        if (checkRoleUserService.isUserOrAdmin(auth.getName(), commentOwner)) {
            if (comment.getAds().getId() != adId) {
                throw new AdNotFoundException();
            }
            commentRepository.delete(comment);
            return true;
        }
        return false;
    }

    @Override
    public CommentDto updateComments(int adId, int commentId, CommentDto commentDto, Authentication auth) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);
        User commentOwner = comment.getAuthor();
        if (checkRoleUserService.isUserOrAdmin(auth.getName(), commentOwner)) {
            if (comment.getAds().getId() != adId) {
                throw new AdNotFoundException();
            }
            comment.setText(commentDto.getText());
            commentRepository.save(comment);
            adsRepository.save(comment.getAds());
            return commentMapper.commentToCommentDto(comment);
        }
        return commentDto;
    }
}
