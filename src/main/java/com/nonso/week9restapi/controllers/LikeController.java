package com.nonso.week9restapi.controllers;

import com.nonso.week9restapi.common.LikeResponse;
import com.nonso.week9restapi.dto.LikeDto;
import com.nonso.week9restapi.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@Slf4j
@RequestMapping("/api")
public class LikeController {

    private final UserService userService;

    @Autowired
    public LikeController(UserService userService) {
        this.userService = userService;
    }


    @ApiOperation(value = "REST API to like a post")
    @PostMapping(value = "/like/{user_id}/{post_id}")
    public ResponseEntity<LikeResponse> likePosts(@PathVariable(value = "user_id") Long userId, @PathVariable(value = "post_id") Long postId, @RequestBody LikeDto likeDto) {
        //log.info("Successfully liked : {} ", likeDto.isLiked());
        return new ResponseEntity<>(userService.like(userId, postId, likeDto), HttpStatus.CREATED);
    }

}
