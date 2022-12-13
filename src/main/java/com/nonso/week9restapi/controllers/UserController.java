package com.nonso.week9restapi.controllers;

import com.nonso.week9restapi.common.*;
import com.nonso.week9restapi.dto.*;
import com.nonso.week9restapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Api(value = "User Controller", description = "REST APIs related to User Entity!!!!. Exposes endpoints for creating, updating, deleting and retrieving users")
@RestController
@RequestMapping(value = "/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "REST API to Register user to Blog application")
    @PostMapping(value = "/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.register(userDto), HttpStatus.CREATED);
    }


    @ApiOperation(value = "REST API to Login user to Blog application")
    @PostMapping(value = "/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
    }

}
