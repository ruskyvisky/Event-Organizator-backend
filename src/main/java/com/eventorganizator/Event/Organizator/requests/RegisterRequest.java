package com.eventorganizator.Event.Organizator.requests;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String username;
    private String password;
}
