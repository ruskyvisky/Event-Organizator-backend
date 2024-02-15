package com.eventorganizator.Event.Organizator.response;

import com.eventorganizator.Event.Organizator.entities.Comment;
import lombok.Data;

@Data
public class CommentResponse {
    private long id;
    private String text;
    private String author;
    private String event;
    private String createDate;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.text = comment.getText();
        this.createDate = comment.getCreateDate().toString();
        this.author = comment.getUser().getUsername();
        this.event = comment.getEvent().getName();
    }
}
