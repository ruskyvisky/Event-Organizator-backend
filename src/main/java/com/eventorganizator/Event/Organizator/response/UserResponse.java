package com.eventorganizator.Event.Organizator.response;

import com.eventorganizator.Event.Organizator.entities.User;
import lombok.Data;

@Data
public class UserResponse {

    long id;
    String username;


    public UserResponse(User user) {
      this.id = user.getId();
      this.username = user.getUsername();
    }

}
