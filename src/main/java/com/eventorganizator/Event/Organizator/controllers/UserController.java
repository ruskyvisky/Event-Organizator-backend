package com.eventorganizator.Event.Organizator.controllers;

import com.eventorganizator.Event.Organizator.entities.User;
import com.eventorganizator.Event.Organizator.response.ApiResponse;
import com.eventorganizator.Event.Organizator.response.UserResponse;
import com.eventorganizator.Event.Organizator.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody User user) {

        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String loginUser() {
        return "User logged in";
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getOneUser(@PathVariable Long id){
        return userService.getOneUser(id);
    }


}
