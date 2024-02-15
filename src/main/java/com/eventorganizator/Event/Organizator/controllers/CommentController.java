package com.eventorganizator.Event.Organizator.controllers;

import com.eventorganizator.Event.Organizator.entities.Comment;
import com.eventorganizator.Event.Organizator.requests.NewCommentRequest;
import com.eventorganizator.Event.Organizator.response.ApiResponse;
import com.eventorganizator.Event.Organizator.services.CommentService;
import com.eventorganizator.Event.Organizator.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping
    public ResponseEntity<ApiResponse> createComment(@RequestBody NewCommentRequest newCommentRequest){
        return commentService.createComment(newCommentRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllComments(){
        return commentService.getAllComments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getComment(@PathVariable Long id){
        return commentService.getComment(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateComment(@PathVariable Long id, @RequestBody NewCommentRequest newCommentRequest){
        return commentService.updateComment(id, newCommentRequest);
    }

    @GetMapping("/event/{id}")
    public ResponseEntity<ApiResponse> getCommentsByEvent(@PathVariable Long id){
        return commentService.getCommentsByEvent(id);
    }


}
