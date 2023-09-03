package com.skyteam.shopping_area.repository;

import com.skyteam.shopping_area.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByAdsId(int id);

    Optional<Comment> deleteAdsComment(Integer adId, Integer id);
}
