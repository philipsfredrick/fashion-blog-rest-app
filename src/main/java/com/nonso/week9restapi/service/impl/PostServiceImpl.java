package com.nonso.week9restapi.service.impl;

import com.nonso.week9restapi.common.CreatePostResponse;
import com.nonso.week9restapi.common.PostResponse;
import com.nonso.week9restapi.common.SearchPostResponse;
import com.nonso.week9restapi.dto.PostDto;
import com.nonso.week9restapi.exceptions.PostNotFoundException;
import com.nonso.week9restapi.exceptions.UserNotFoundException;
import com.nonso.week9restapi.model.Post;
import com.nonso.week9restapi.model.User;
import com.nonso.week9restapi.repository.PostRepository;
import com.nonso.week9restapi.repository.UserRepository;
import com.nonso.week9restapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    //private ModelMapper mapper;


    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");


    @Autowired
    public PostServiceImpl(PostRepository postRepository,UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }



    @Override
    public CreatePostResponse createPost(PostDto postDto) {
        Post post = new Post();
        User user = findUserById(postDto.getUser_id());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setSlug(makeSlug(postDto.getTitle()));
        post.setFeaturedImage(postDto.getFeaturedImage());
        post.setUser(user);
        postRepository.save(post);
        return new CreatePostResponse("success", LocalDateTime.now(), post);
    }

    @Override
    public SearchPostResponse searchPost(String keyword) {
        List<Post> postList = postRepository.findByTitleContaining(keyword);
        return new SearchPostResponse("success", LocalDateTime.now(), postList);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        PostResponse postResponse = new PostResponse();
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    public Post getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->
                new PostNotFoundException("Post with id " + postId + " not found"));

        return post;
    }



    @Override
    public Post updatePost(PostDto postDto, Long postId) {
        // get post by id from database
        Post post = postRepository.findById(postId).get();

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setFeaturedImage(postDto.getFeaturedImage());
        post.setUpdatedAt(LocalDateTime.now());

        return postRepository.save(post);

    }

    @Override
    public void deletePostById(Long postId) {
        // get post by id from the database
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException("Post with ID: " + postId + " Not Found"));
        postRepository.delete(post);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with ID: " + id + " Not Found"));
    }

//
//    public User findUserByEmail(String email) {
//        return userRepository.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("User with email: " + email + "Not Found"));
//    }


    public String makeSlug(String input) {
        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }
//
//    //convert Entity into Dto
//    private PostDto mapToDTO(Post post) {
//        PostDto postDto = mapper.map(post, PostDto.class);
//        return postDto;
//    }
//
//    // convert Dto to Entity
//    private Post mapToEntity(PostDto postDto) {
//        Post post = mapper.map(postDto, Post.class);
//        return post;
}

