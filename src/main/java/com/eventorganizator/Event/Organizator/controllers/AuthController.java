package com.eventorganizator.Event.Organizator.controllers;

import com.eventorganizator.Event.Organizator.requests.AuthRequest;
import com.eventorganizator.Event.Organizator.requests.RegisterRequest;
import com.eventorganizator.Event.Organizator.response.ApiResponse;
import com.eventorganizator.Event.Organizator.services.AuthService;
import com.eventorganizator.Event.Organizator.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody RegisterRequest registerRequest) {

        return authService.registerUser(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody AuthRequest authRequest) {

        return authService.loginUser(authRequest);
    }
}
