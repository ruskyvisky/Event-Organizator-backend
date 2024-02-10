package com.eventorganizator.Event.Organizator.services;

import com.eventorganizator.Event.Organizator.entities.User;
import com.eventorganizator.Event.Organizator.messages.Message;
import com.eventorganizator.Event.Organizator.repositories.UserRepository;
import com.eventorganizator.Event.Organizator.response.ApiResponse;
import com.eventorganizator.Event.Organizator.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    public ResponseEntity<ApiResponse> registerUser(User user){
        userRepo.save(user);

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message(Message.SUCCESS.getDesc())
                        .build(),
                HttpStatus.OK
        );

    }

    public ResponseEntity<UserResponse> getOneUser(Long id){
        User user = userRepo.findById(id).orElse(null);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        UserResponse userResponse = new UserResponse(user);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> getAllUsers(){
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message(Message.SUCCESS.getDesc())
                        .data(userRepo.findAll().stream().map(UserResponse::new).collect(Collectors.toList()))
                        .build(),
                HttpStatus.OK
        );


    }


}
