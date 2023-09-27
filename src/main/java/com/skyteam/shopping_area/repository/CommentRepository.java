package com.skyteam.shopping_area.repository;

import com.skyteam.shopping_area.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByAdsId(int id);

}
