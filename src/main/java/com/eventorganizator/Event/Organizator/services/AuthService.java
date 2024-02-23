package com.eventorganizator.Event.Organizator.services;

import com.eventorganizator.Event.Organizator.entities.User;
import com.eventorganizator.Event.Organizator.messages.Message;
import com.eventorganizator.Event.Organizator.repositories.UserRepository;
import com.eventorganizator.Event.Organizator.requests.AuthRequest;
import com.eventorganizator.Event.Organizator.requests.RegisterRequest;
import com.eventorganizator.Event.Organizator.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepo;
    private final BCryptPasswordEncoder passwordEncoder;


    private final JwtService jwtService;

    public AuthService(UserRepository userRepo, BCryptPasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;

    }


    public ResponseEntity<ApiResponse> registerUser(RegisterRequest registerRequest){
        if(userRepo.existsByUsername(registerRequest.getUsername())){
            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .message(Message.USERNAME_EXISTS.getDesc())
                            .build(),
                    HttpStatus.BAD_REQUEST
            );
        }
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepo.save(newUser);

        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message(Message.SUCCESS.getDesc())
                        .build(),
                HttpStatus.OK
        );

    }

    public ResponseEntity<ApiResponse> loginUser(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            String token =  jwtService.generateToken(authRequest.getUsername());

            return new ResponseEntity<>(
                    ApiResponse.builder()
                            .message(Message.SUCCESS.getDesc())
                            .data(token)
                            .build(),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                ApiResponse.builder()
                        .message(Message.BAD_REQUEST.getDesc())
                        .build(),
                HttpStatus.BAD_REQUEST
        );

    }

}
