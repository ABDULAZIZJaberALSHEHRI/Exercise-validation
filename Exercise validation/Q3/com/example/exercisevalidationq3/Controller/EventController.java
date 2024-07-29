package com.example.exercisevalidationq3.Controller;

import com.example.exercisevalidationq3.ApiResponse.ApiResponse;
import com.example.exercisevalidationq3.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/event-system")
public class EventController {
    ArrayList<Event> events = new ArrayList<Event>();

    @PostMapping("/add-event")
    public ResponseEntity addEvent(@Valid @RequestBody Event event, Errors errors) {
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponse("event added successfully"));
    }

    @GetMapping("/display-events")
    public ResponseEntity getEvents() {
        return ResponseEntity.status(200).body(events);
    }

    @PutMapping("/update-event/{index}")
    public ResponseEntity updateEvent(@PathVariable int index,@Valid @RequestBody Event event, Errors errors) {
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.set(index,event);
        return ResponseEntity.status(200).body(new ApiResponse("event updated successfully"));
    }

    @DeleteMapping("/delete-event/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index) {
        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("event deleted successfully"));
    }

    @PutMapping("/change-capacity/{index}")
    public ResponseEntity changeCapacity(@PathVariable int index,@Valid @RequestBody int newCapacity,Errors errors) {
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.get(index).setCapacity(newCapacity);
        return ResponseEntity.status(200).body(new ApiResponse("Capacity updated successfully"));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity searchEvent(@PathVariable String id) {
        for (Event e : events) {
            if(e.getId().equalsIgnoreCase(id)) {
                return ResponseEntity.status(200).body(e);
            }
        }
        return ResponseEntity.status(404).body(new ApiResponse("Event not found"));
    }
}
