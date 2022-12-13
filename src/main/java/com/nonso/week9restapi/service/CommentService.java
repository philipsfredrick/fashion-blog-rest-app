package com.nonso.week9restapi.service;

import com.nonso.week9restapi.common.CommentResponse;
import com.nonso.week9restapi.common.SearchCommentResponse;
import com.nonso.week9restapi.dto.CommentDto;
import com.nonso.week9restapi.model.Comment;

import java.util.List;

public interface CommentService {

    CommentResponse comment(long userId, long postId, CommentDto commentDto);
    SearchCommentResponse searchComment(String keyword);
    //List<CommentDto> getCommentByPostId(long postId);
//    CommentDto getCommentById(Long postId, Long commentId);
    //CommentDto updateComment(long postId, long commentId, CommentDto commentDto);

    Comment getCommentById(Long postId, Long commentId);

    Comment updateComment(Long postId, Long commentId, CommentDto commentDto);

    void deleteComment(Long postId, Long commentId);

}
