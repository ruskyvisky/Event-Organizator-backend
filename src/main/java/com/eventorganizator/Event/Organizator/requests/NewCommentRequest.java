package com.eventorganizator.Event.Organizator.requests;

import lombok.Data;

@Data
public class NewCommentRequest {
    private Long eventId;
    private String text;
    private Long userId;


}
