package com.nonso.week9restapi.common;

import com.nonso.week9restapi.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class RegisterResponse {
    private String message;
    private LocalDateTime timeStamp;
    private User user;
}
