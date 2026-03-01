package com.example.workflow.controller;

import com.example.workflow.entities.User;
import com.example.workflow.payload.ApiResponse;
import com.example.workflow.security.JwtUtil;
import com.example.workflow.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import com.example.workflow.payload.request.LoginRequest;
@RestController
@RequestMapping("/api/users")
public class UserController {

    private  UserService userService;
    private  AuthenticationManager authenticationManager;
    private  JwtUtil jwtUtil;

    public UserController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(
            @RequestBody User user) {

        User savedUser = userService.registerUser(user);

        return ResponseEntity.ok(
                new ApiResponse<>(true, "User registered successfully", savedUser)
        );
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(
            @RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        String token = jwtUtil.generateToken(request.getEmail());

        return ResponseEntity.ok(
                new ApiResponse<>(true, "Login successful", token)
        );
    }
}
