package com.nonso.week9restapi.common;

import com.nonso.week9restapi.model.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CreatePostResponse {
    private String message;
    private LocalDateTime timeStamp;
    private Post post;
}
