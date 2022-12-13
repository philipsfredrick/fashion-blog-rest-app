package com.nonso.week9restapi.service.impl;

import com.nonso.week9restapi.common.*;
import com.nonso.week9restapi.dto.*;
import com.nonso.week9restapi.exceptions.PostAlreadyLikedException;
import com.nonso.week9restapi.exceptions.PostNotFoundException;
import com.nonso.week9restapi.exceptions.UserNotFoundException;
import com.nonso.week9restapi.model.Like;
import com.nonso.week9restapi.model.Post;
import com.nonso.week9restapi.model.User;
import com.nonso.week9restapi.repository.CommentRepository;
import com.nonso.week9restapi.repository.LikeRepository;
import com.nonso.week9restapi.repository.PostRepository;
import com.nonso.week9restapi.repository.UserRepository;
import com.nonso.week9restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    @Autowired
    public UserServiceImpl(LikeRepository likeRepository, UserRepository userRepository, CommentRepository commentRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;

    }


    @Override
    public RegisterResponse register(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        userRepository.save(user);

        return new RegisterResponse("success", LocalDateTime.now(), user);
    }

    @Override
    public LoginResponse login(LoginDto loginDto) {
        User guest = findUserByEmail(loginDto.getEmail());
        LoginResponse loginResponse = null;
        if (guest != null) {
            if (guest.getPassword().equals(loginDto.getPassword())) {
                loginResponse = new LoginResponse("success", LocalDateTime.now());
            }
        } else {
            loginResponse = new LoginResponse("password MisMatch", LocalDateTime.now());
        }

        return loginResponse;
    }





    @Override
    public LikeResponse like(Long userId, Long postId, LikeDto likeDto) {
        Like like = new Like();
        User user = findUserById(userId);
        Post post = findPostById(postId);
        LikeResponse likeResponse = null;
        Like duplicateLike = likeRepository.findLikeByUserIdAndPostId(userId, postId);
        if (duplicateLike == null) {
            like.setLiked(likeDto.isLiked());
            like.setUser(user);
            like.setPost(post);
            likeRepository.save(like);
            List<Like> likeList = likeRepository.likeList(postId);
            likeResponse = new LikeResponse("success", LocalDateTime.now(), like, likeList.size());

        } else {
            likeRepository.delete(duplicateLike);
            throw new PostAlreadyLikedException("This post has been liked, It is now Unliked :(");
        }
        return likeResponse;
    }




    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with ID: " + userId + " Not Found"));
    }


    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User with email: " + email + "Not Found"));
    }

    public Post findPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post with ID: " + postId + " Not Found"));
    }






}
