package com.nonso.week9restapi.controllers;

import com.nonso.week9restapi.common.CommentResponse;
import com.nonso.week9restapi.common.SearchCommentResponse;
import com.nonso.week9restapi.dto.CommentDto;
import com.nonso.week9restapi.model.Comment;
import com.nonso.week9restapi.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Comment Controller", description = "REST APIs related to Comment Entity!!!!. Exposes endpoints for creating, updating, deleting and retrieving comments")
@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController( CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "REST API to create a comment")
    @PostMapping(value = "/comment/{userId}/{postId}")
    public ResponseEntity<CommentResponse> commentsOnPosts(@PathVariable(value = "userId") Long userId, @PathVariable(value = "postId") Long postId, @RequestBody CommentDto commentDto) {
        //log.info("Successfully commented : {} ", commentDto.getComment());
        return new ResponseEntity<>(commentService.comment(userId, postId, commentDto), HttpStatus.CREATED);
    }


    @ApiOperation(value = "REST API to search for a comment")
    @GetMapping(value = "/searchComment/{keyword}")
    public ResponseEntity<SearchCommentResponse> searchComments(@PathVariable(value = "keyword") String keyword) {
        return new ResponseEntity<>(commentService.searchComment(keyword), HttpStatus.OK);
    }
//
//    @GetMapping("/posts/{postId}/comments")
//    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") Long postId) {
//        return commentService.getCommentsByPostId(postId);
//    }

    @ApiOperation(value = "REST API to get  comment by id")
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable(value = "postId") Long postId, @PathVariable(value = "id") Long commentId) {
        Comment comment = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @ApiOperation(value = "REST API to update comment")
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable(value = "postId") Long postId,
                                                    @PathVariable(value = "id") Long commentId,
                                                    @RequestBody CommentDto commentDto) {
        Comment updated = commentService.updateComment(postId, commentId, commentDto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @ApiOperation(value = "REST API to delete comment")
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId,
                                                @PathVariable(value = "id") Long commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }
}
