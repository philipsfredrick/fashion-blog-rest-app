package com.nonso.week9restapi.repository;

import com.nonso.week9restapi.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    @Query(value = "SELECT * FROM likes WHERE user_id = ?1 AND post_id = ?2", nativeQuery = true)
    Like findLikeByUserIdAndPostId(long userId, long postId);

    @Query(value = "SELECT * FROM likes WHERE post_id = ?1", nativeQuery = true)
    List<Like> likeList(long postId);
}
