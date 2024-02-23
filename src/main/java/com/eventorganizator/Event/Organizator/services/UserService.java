package com.eventorganizator.Event.Organizator.services;

import com.eventorganizator.Event.Organizator.config.PasswordEncoderConfig;
import com.eventorganizator.Event.Organizator.entities.User;
import com.eventorganizator.Event.Organizator.messages.Message;
import com.eventorganizator.Event.Organizator.repositories.UserRepository;
import com.eventorganizator.Event.Organizator.requests.AuthRequest;
import com.eventorganizator.Event.Organizator.requests.RegisterRequest;
import com.eventorganizator.Event.Organizator.response.ApiResponse;
import com.eventorganizator.Event.Organizator.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.persistence.EntityNotFoundException;


import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {


    private final UserRepository userRepo;



    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);
        return user.orElseThrow(EntityNotFoundException::new);
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
