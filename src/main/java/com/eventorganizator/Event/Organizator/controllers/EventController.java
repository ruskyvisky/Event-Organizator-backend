package com.eventorganizator.Event.Organizator.controllers;

import com.eventorganizator.Event.Organizator.entities.Event;
import com.eventorganizator.Event.Organizator.requests.NewEventRequest;
import com.eventorganizator.Event.Organizator.response.ApiResponse;
import com.eventorganizator.Event.Organizator.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createEvent(@RequestBody NewEventRequest newEventRequest) {
        return eventService.createEvent(newEventRequest);
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getEvent(@PathVariable Long id){
        return eventService.getSingleEvent(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEvent(@PathVariable Long id){
        return eventService.deleteEvent(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateEvent(@PathVariable Long id, @RequestBody NewEventRequest newEventRequest){
        return eventService.updateEvent(id, newEventRequest);
    }

    @PostMapping("/join/{id}")
    public ResponseEntity<ApiResponse> joinEvent(@PathVariable Long id){
        return eventService.joinEvent(id);
    }
}
