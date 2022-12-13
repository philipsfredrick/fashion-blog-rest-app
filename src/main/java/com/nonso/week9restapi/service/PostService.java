package com.nonso.week9restapi.service;

import com.nonso.week9restapi.common.CreatePostResponse;
import com.nonso.week9restapi.common.PostResponse;
import com.nonso.week9restapi.common.SearchPostResponse;
import com.nonso.week9restapi.dto.PostDto;
import com.nonso.week9restapi.model.Post;

public interface PostService {

    CreatePostResponse createPost(PostDto postDto);
    SearchPostResponse searchPost(String keyword);
    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
    Post getPostById(Long postId);
//    Post updatePost(PostDto postDto, int postId);
//    void deletePostById(int postId);

    Post updatePost(PostDto postDto, Long postId);

    void deletePostById(Long postId);
}
