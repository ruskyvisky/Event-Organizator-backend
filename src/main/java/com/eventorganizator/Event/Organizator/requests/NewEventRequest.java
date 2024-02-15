package com.eventorganizator.Event.Organizator.requests;

import com.eventorganizator.Event.Organizator.entities.User;
import lombok.Data;

import java.util.Date;

@Data
public class NewEventRequest {
     long id;
     String name;
     String description;
     String location;
     Long creatorId;
     boolean isPublic;
     Date date;
}
