package com.nonso.week9restapi.controllers;

import com.nonso.week9restapi.common.CreatePostResponse;
import com.nonso.week9restapi.common.PostResponse;
import com.nonso.week9restapi.common.SearchPostResponse;
import com.nonso.week9restapi.dto.PostDto;
import com.nonso.week9restapi.model.Post;
import com.nonso.week9restapi.service.PostService;
import com.nonso.week9restapi.service.UserService;
import com.nonso.week9restapi.utils.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Api(value = "CRUD REST API for Posts resources", description = "Exposes endpoints for creating, updating, deleting and retrieving posts")
@RestController
@RequestMapping("/api")
public class PostController {

    private final UserService userService;
    private final PostService postService;


    @Autowired
    public PostController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }


    @ApiOperation(value = "REST API to create a new post")
    @PostMapping(value = "/create")
    public ResponseEntity<CreatePostResponse> createPost(@RequestBody PostDto postDto) {
        //log.info("Successfully Created A post With Title: {} " , postDto.getTitle());
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "REST API to retrieve all posts")
    @GetMapping("/posts")
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @ApiOperation(value = "REST API to retrieve a post by id")
    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable(value = "postId") Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @ApiOperation(value = "REST API to delete a post")
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable(value = "postId") Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }


    @ApiOperation(value = "REST API to update a post")
    @PutMapping("/posts/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable(value = "postId") Long id, @RequestBody PostDto postDto) {

        Post postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @ApiOperation(value = "REST API to search for a post by title")
    @GetMapping(value = "/searchPost/{keyword}")
    public ResponseEntity<SearchPostResponse> searchPosts(@PathVariable(value = "keyword") String keyword) {
        return new ResponseEntity<>(postService.searchPost(keyword), HttpStatus.OK);
    }
}
