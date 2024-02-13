package com.eventorganizator.Event.Organizator.response;

import lombok.Data;

@Data
public class EventResponse {
    long id;
    String name;
    String description;
    String date;
    String location;
    String creator;
    boolean isPublic;

    public EventResponse(long id, String name, String description, String date, String location, String creator, boolean isPublic) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
        this.creator = creator;
        this.isPublic = isPublic;
    }
}
