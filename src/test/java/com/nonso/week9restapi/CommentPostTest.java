package com.nonso.week9restapi;

import com.nonso.week9restapi.model.Comment;
import com.nonso.week9restapi.model.Post;
import com.nonso.week9restapi.model.User;
import com.nonso.week9restapi.repository.CommentRepository;
import com.nonso.week9restapi.repository.PostRepository;
import com.nonso.week9restapi.repository.UserRepository;
import com.nonso.week9restapi.service.impl.CommentServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentPostTest {
    @Mock
    UserRepository userRepository;

    @Mock
    PostRepository postRepository;

    @Mock
    CommentRepository commentRepository;

    @InjectMocks
    CommentServiceImpl commentServiceImpl;

    private User user;

    private Post post;

    private Comment comment;
}
