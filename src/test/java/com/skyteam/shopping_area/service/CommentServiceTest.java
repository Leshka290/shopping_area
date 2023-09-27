package com.skyteam.shopping_area.service;

import com.skyteam.shopping_area.dto.CommentDto;
import com.skyteam.shopping_area.dto.ResponseWrapperCommentDto;
import com.skyteam.shopping_area.exception.AdNotFoundException;
import com.skyteam.shopping_area.exception.CommentNotFoundException;
import com.skyteam.shopping_area.mapper.CommentMapper;
import com.skyteam.shopping_area.model.Ad;
import com.skyteam.shopping_area.model.Comment;
import com.skyteam.shopping_area.model.User;
import com.skyteam.shopping_area.repository.AdsRepository;
import com.skyteam.shopping_area.repository.CommentRepository;
import com.skyteam.shopping_area.repository.UserRepository;
import com.skyteam.shopping_area.service.CheckRoleUserService;
import com.skyteam.shopping_area.service.CommentService;
import com.skyteam.shopping_area.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AdsRepository adsRepository;

    @Mock
    private CheckRoleUserService checkRoleUserService;

    @Mock
    private CommentMapper commentMapper;

    @Mock
    private Authentication authentication;

    private CommentService commentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        commentService = new CommentServiceImpl(commentRepository, userRepository, adsRepository, checkRoleUserService, commentMapper);
    }







    @Test
    public void testDeleteComments_CommentNotFound() {
        int adId = 1;
        int commentId = 1;

        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

        assertThrows(CommentNotFoundException.class, () -> commentService.deleteComments(adId, commentId, authentication));
        verify(commentRepository, never()).delete(any(Comment.class));
    }

    @Test
    public void testDeleteComments_NotAuthorized() {
        int adId = 1;
        int commentId = 1;

        Comment comment = new Comment();
        comment.setId(commentId);

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        when(checkRoleUserService.isUserOrAdmin(anyString(), any(User.class))).thenReturn(false);

        assertFalse(commentService.deleteComments(adId, commentId, authentication));
        verify(commentRepository, never()).delete(any(Comment.class));
    }



    @Test
    public void testUpdateComments_Success() {
        int adId = 1;
        int commentId = 1;
        CommentDto commentDto = new CommentDto();
        commentDto.setText("Updated Comment");

        Comment comment = new Comment();
        comment.setId(commentId);
        comment.setText("Test Comment");

        User user = new User();
        user.setEmail("test@test.com");

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        when(checkRoleUserService.isUserOrAdmin("test@test.com", user)).thenReturn(true);
        when(adsRepository.findById(adId)).thenReturn(Optional.of(new Ad()));

        CommentDto result = commentService.updateComments(adId, commentId, commentDto, authentication);

        assertNotNull(result);
        assertEquals("Updated Comment", result.getText());
    }

    @Test
    public void testUpdateComments_CommentNotFound() {
        int adId = 1;
        int commentId = 1;
        CommentDto commentDto = new CommentDto();
        commentDto.setText("Updated Comment");

        when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

        assertThrows(CommentNotFoundException.class, () -> commentService.updateComments(adId, commentId, commentDto, authentication));
        verify(commentRepository, never()).save(any(Comment.class));
    }

    @Test
    public void testUpdateComments_NotAuthorized() {
        int adId = 1;
        int commentId = 1;
        CommentDto commentDto = new CommentDto();
        commentDto.setText("Updated Comment");

        Comment comment = new Comment();
        comment.setId(commentId);

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));
        when(checkRoleUserService.isUserOrAdmin(anyString(), any(User.class))).thenReturn(false);

        CommentDto result = commentService.updateComments(adId, commentId, commentDto, authentication);

        assertNotNull(result);
        assertEquals("Updated Comment", result.getText());
        verify(commentRepository, never()).save(any(Comment.class));
    }


}
