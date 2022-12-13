package com.nonso.week9restapi.service.impl;


import com.nonso.week9restapi.common.CommentResponse;
import com.nonso.week9restapi.common.SearchCommentResponse;
import com.nonso.week9restapi.dto.CommentDto;
import com.nonso.week9restapi.exceptions.CommentNotFoundException;
import com.nonso.week9restapi.exceptions.PostNotFoundException;
import com.nonso.week9restapi.exceptions.UserNotFoundException;
import com.nonso.week9restapi.model.Comment;
import com.nonso.week9restapi.model.Post;
import com.nonso.week9restapi.model.User;
import com.nonso.week9restapi.repository.CommentRepository;
import com.nonso.week9restapi.repository.PostRepository;
import com.nonso.week9restapi.repository.UserRepository;
import com.nonso.week9restapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    //private ModelMapper mapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentResponse comment(long userId, long postId, CommentDto commentDto) {
        User user = findUserById(userId);
        Post post = findPostById(postId);
        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);

        return new CommentResponse("success", LocalDateTime.now(), comment, post);
    }


    @Override
    public SearchCommentResponse searchComment(String keyword) {
        List<Comment> commentList = commentRepository.findByCommentContaining(keyword);
        return new SearchCommentResponse("success", LocalDateTime.now(), commentList);
    }

//    @Override
//    public List<CommentDto> getCommentByPostId(long postId) {
//        // retrieve comments by postId
//        List<Comment> comments = commentRepository.findByPostId(postId);
//
//        // convert list of comment entities to list of comment dtos'
//        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
//    }

    @Override
    public Comment getCommentById(Long postId, Long commentId) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException("Post with ID: " + postId + " Not found"));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new CommentNotFoundException("Comment with ID: " + commentId + " Not Found"));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new CommentNotFoundException("Comment with ID: " + commentId + " does not belong to post");
        }
        return comment;
    }

    @Override
    public Comment updateComment(Long postId, Long commentId, CommentDto commentDto) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException("Post with ID: " + postId + " Not Found"));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new CommentNotFoundException("Comment with ID: " + commentId + " Not Found"));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new CommentNotFoundException("Comment with ID: " + commentId + " Not for post");
        }
        comment.setComment(commentDto.getComment());

        commentRepository.save(comment);

        return comment;
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        // retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException("Post with ID: " + postId + " Not Found"));

        // retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new CommentNotFoundException("Comment with ID: " + commentId + " Not Found"));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new CommentNotFoundException("Comment with ID: " + commentId + " Not for post");
        }

        commentRepository.delete(comment);
    }
//
//    private CommentDto mapToDto(Comment comment) {
//        CommentDto commentDto = mapper.map(comment, CommentDto.class);
//        return commentDto;
//    }
//
//    private Comment mapToEntity(CommentDto commentDto) {
//        Comment comment = mapper.map(commentDto, Comment.class);
//        return comment;
//    }



    public User findUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with ID: " + userId + " Not Found"));
    }

//
//    public User findUserByEmail(String email) {
//        return userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User with email: " + email + "Not Found"));
//    }

    public Post findPostById(long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post with ID: " + postId + " Not Found"));
    }
}
