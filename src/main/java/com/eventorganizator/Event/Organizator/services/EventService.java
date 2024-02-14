package com.eventorganizator.Event.Organizator.services;

import com.eventorganizator.Event.Organizator.entities.Event;
import com.eventorganizator.Event.Organizator.entities.User;
import com.eventorganizator.Event.Organizator.messages.Message;
import com.eventorganizator.Event.Organizator.repositories.EventRepository;
import com.eventorganizator.Event.Organizator.repositories.UserRepository;
import com.eventorganizator.Event.Organizator.requests.NewEventRequest;
import com.eventorganizator.Event.Organizator.response.ApiResponse;
import com.eventorganizator.Event.Organizator.response.EventResponse;
import com.eventorganizator.Event.Organizator.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class EventService {

     final EventRepository eventRepo;
     final UserRepository userRepo;
     final UserService userService;
    public EventService(EventRepository eventRepo, UserService userService,UserRepository userRepo){
        this.userRepo = userRepo;
        this.userService = userService;
        this.eventRepo = eventRepo;
    }

    public ResponseEntity<ApiResponse> createEvent(NewEventRequest newEventReq) {
       User user = userRepo.findById(newEventReq.getCreatorId()).orElse(null);
        if(user == null){
            return ResponseEntity.badRequest().body(ApiResponse.builder().message(Message.USER_NOT_FOUND.getDesc()).build());
        }
        Event event = new Event();
        event.setName(newEventReq.getName());
        event.setDescription(newEventReq.getDescription());
        event.setLocation(newEventReq.getLocation());
        event.setCreator(user);
        event.setPublic(newEventReq.isPublic());
       Event savedEvent =  eventRepo.save(event);
        return ResponseEntity.ok(ApiResponse.builder().message(Message.SUCCESS.getDesc()).data(savedEvent).build());


    }
    public ResponseEntity<ApiResponse> getAllEvents(){

        return ResponseEntity.ok(ApiResponse.builder().message(Message.SUCCESS.getDesc())
                .data(eventRepo.findAll().stream()
                        .map(EventResponse::new)
                        .collect(Collectors.toList()))
                .build());
    }


    public ResponseEntity<ApiResponse> deleteEvent(Long id) {
        Optional<Event> existingEvent = eventRepo.findById(id);
        if(existingEvent.isEmpty()){
            return ResponseEntity.badRequest().body(ApiResponse.builder().message(Message.EVENT_NOT_FOUND.getDesc()).build());
        }
        eventRepo.deleteById(id);
        return ResponseEntity.ok(ApiResponse.builder().message(Message.SUCCESS.getDesc()).build());
    }

    public ResponseEntity<ApiResponse> getSingleEvent(Long id) {
Optional<Event> existingEvent = eventRepo.findById(id);
        if(existingEvent.isEmpty()){
            return ResponseEntity.badRequest().body(ApiResponse.builder().message(Message.EVENT_NOT_FOUND.getDesc()).build());
        }
        return ResponseEntity.ok(ApiResponse.builder().message(Message.SUCCESS.getDesc())
                .data(new EventResponse(existingEvent.get()))
                .build());
    }


    public ResponseEntity<ApiResponse> updateEvent(Long id, NewEventRequest newEventRequest) {
        Optional<Event> existingEvent = eventRepo.findById(id);
        if(existingEvent.isEmpty()){
            return ResponseEntity.badRequest().body(ApiResponse.builder().message(Message.EVENT_NOT_FOUND.getDesc()).build());
        }
        Event event = existingEvent.get();
        event.setName(newEventRequest.getName());
        event.setDescription(newEventRequest.getDescription());
        event.setLocation(newEventRequest.getLocation());
        event.setPublic(newEventRequest.isPublic());
        Event savedEvent = eventRepo.save(event);
        return ResponseEntity.ok(ApiResponse.builder().message(Message.SUCCESS.getDesc()).data(savedEvent).build());
    }


    public ResponseEntity<ApiResponse> joinEvent(Long id) {
        Optional<Event> existingEvent = eventRepo.findById(id);
        if(existingEvent.isEmpty()){
            return ResponseEntity.badRequest().body(ApiResponse.builder().message(Message.EVENT_NOT_FOUND.getDesc()).build());
        }
        Event event = existingEvent.get();
        User user = userRepo.findById(1L).orElse(null); // burası değişecek
        if(event.getParticipants().contains(user)){
            return ResponseEntity.badRequest().body(ApiResponse.builder().message("Message.ALREADY_JOINED.getDesc()").build());
        }
        event.getParticipants().add(user);
        Event savedEvent = eventRepo.save(event);
        return ResponseEntity.ok(ApiResponse.builder().message(Message.SUCCESS.getDesc()).data(savedEvent).build());
    }

    public ResponseEntity<ApiResponse> getEventParticipants(Long id) {
        Optional<Event> existingEvent = eventRepo.findById(id);
        if(existingEvent.isEmpty()){
            return ResponseEntity.badRequest().body(ApiResponse.builder().message(Message.EVENT_NOT_FOUND.getDesc()).build());
        }
        Event event = existingEvent.get();
        return ResponseEntity.ok(ApiResponse.builder().message(Message.SUCCESS.getDesc())
                .data(event.getParticipants().stream().map(UserResponse::new).collect(Collectors.toList()))
                .build());
    }
}
