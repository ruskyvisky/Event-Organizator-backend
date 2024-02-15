package com.eventorganizator.Event.Organizator.response;

import com.eventorganizator.Event.Organizator.entities.Comment;
import com.eventorganizator.Event.Organizator.entities.Event;
import com.eventorganizator.Event.Organizator.entities.User;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class EventResponse {
    private long id;
    private String name;
    private String description;
    private Date date;
    private String location;
    private String creator;
    private List<String> participants;
    private List<CommentResponse> comments;
    private boolean isPublic;

    public EventResponse(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
        this.location = event.getLocation();
        this.date = event.getDate();
        this.comments = event.getComments()
                .stream()
                .map(CommentResponse::new)
                .toList();
        this.creator = event.getCreator().getUsername();
        this.isPublic = event.isPublic();
        this.participants = event.getParticipants().stream().map(User::getUsername).toList();
    }
}