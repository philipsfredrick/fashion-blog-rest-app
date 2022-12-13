package com.nonso.week9restapi.service;

import com.nonso.week9restapi.common.*;
import com.nonso.week9restapi.dto.*;

public interface UserService {

    RegisterResponse register(UserDto userDto);
    LoginResponse login(LoginDto loginDto);
    //LikeResponse like(int userId, int postId, LikeDto likeDto);


    LikeResponse like(Long userId, Long postId, LikeDto likeDto);
}
