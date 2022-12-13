package com.nonso.week9restapi.repository;

import com.nonso.week9restapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByCommentContaining(String keyword);
    List<Comment> findByPostId(long postId);
}
