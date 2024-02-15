package com.eventorganizator.Event.Organizator.services;

import com.eventorganizator.Event.Organizator.entities.Comment;
import com.eventorganizator.Event.Organizator.entities.Event;
import com.eventorganizator.Event.Organizator.entities.User;
import com.eventorganizator.Event.Organizator.messages.Message;
import com.eventorganizator.Event.Organizator.repositories.CommentRepository;
import com.eventorganizator.Event.Organizator.repositories.EventRepository;
import com.eventorganizator.Event.Organizator.repositories.UserRepository;
import com.eventorganizator.Event.Organizator.requests.NewCommentRequest;
import com.eventorganizator.Event.Organizator.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    UserRepository userRepo;
    EventService eventService;
    CommentRepository commentRepo;
    EventRepository eventRepo;


    public CommentService(UserRepository userRepo, EventService eventService,CommentRepository commentRepo,EventRepository eventRepo){
        this.userRepo = userRepo;
        this.commentRepo = commentRepo;
        this.eventRepo = eventRepo;
        this.eventService = eventService;
    }


    public  ResponseEntity<ApiResponse> createComment(NewCommentRequest commentRequest) {
        User user = userRepo.findById(commentRequest.getUserId()).orElse(null);
        if(user == null){
            return ResponseEntity.badRequest().body(ApiResponse.builder().message(Message.USER_NOT_FOUND.getDesc()).build());
        }
        Event event = eventRepo.findById(commentRequest.getEventId()).orElse(null);
        Comment comment = new Comment();
        comment.setText(commentRequest.getText());
        comment.setUser(user);
        comment.setEvent(event);
        commentRepo.save(comment);
        return ResponseEntity.ok(ApiResponse.builder().message(Message.SUCCESS.getDesc()).data(comment).build());


    }

    public ResponseEntity<ApiResponse> getComment(Long id) {
        return ResponseEntity.ok(ApiResponse.builder().message(Message.SUCCESS.getDesc()).data(commentRepo.findById(id).orElse(null)).build());
    }

    public ResponseEntity<ApiResponse> getAllComments() {
        return ResponseEntity.ok(ApiResponse.builder().message(Message.SUCCESS.getDesc()).data(commentRepo.findAll()).build());
    }

    public ResponseEntity<ApiResponse> deleteComment(Long id) {
        commentRepo.deleteById(id);
        return ResponseEntity.ok(ApiResponse.builder().message(Message.SUCCESS.getDesc()).build());
    }

    public ResponseEntity<ApiResponse> updateComment(Long id, NewCommentRequest newCommentRequest) {
        Comment comment = commentRepo.findById(id).orElse(null);
        if(comment == null){
            return ResponseEntity.badRequest().body(ApiResponse.builder().message(Message.COMMENT_NOT_FOUND.getDesc()).build());
        }
        comment.setText(newCommentRequest.getText());
        commentRepo.save(comment);
        return ResponseEntity.ok(ApiResponse.builder().message(Message.SUCCESS.getDesc()).data(comment).build());
    }

    public ResponseEntity<ApiResponse> getCommentsByEvent(Long id) {
        Event event = eventRepo.findById(id).orElse(null);
        if(event == null){
            return ResponseEntity.badRequest().body(ApiResponse.builder().message(Message.EVENT_NOT_FOUND.getDesc()).build());
        }
        return ResponseEntity.ok(ApiResponse.builder().message(Message.SUCCESS.getDesc()).data(commentRepo.findAllByEvent(event)).build());
    }
}
