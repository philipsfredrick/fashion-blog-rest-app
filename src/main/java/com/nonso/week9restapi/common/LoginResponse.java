package com.nonso.week9restapi.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String message;
    private LocalDateTime timeStamp;
}
