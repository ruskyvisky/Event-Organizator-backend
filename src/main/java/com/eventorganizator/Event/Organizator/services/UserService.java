package com.eventorganizator.Event.Organizator.services;

import com.eventorganizator.Event.Organizator.entities.User;
import com.eventorganizator.Event.Organizator.messages.Message;
import com.eventorganizator.Event.Organizator.repositories.UserRepository;
import com.eventorganizator.Event.Organizator.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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



}
