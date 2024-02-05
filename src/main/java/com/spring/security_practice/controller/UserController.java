package com.spring.security_practice.controller;

import com.spring.security_practice.domain.User;
import com.spring.security_practice.dto.UserRequest;
import com.spring.security_practice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    // @PostMapping
    @PostMapping
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRequest userRequest){
        userService.saveUser(userRequest);
        return ResponseEntity.ok("User is created successfully...");
    }

}
