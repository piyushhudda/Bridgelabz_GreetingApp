package com.bridgelabz.greetingapp;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greeting")
public class GreetingAppController {

    private final GreetingService greetingService;

    public GreetingAppController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    // Handle GET request with optional firstName and lastName
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> getGreeting(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        String message = GreetingService.getGreeting(firstName, lastName);
        return Map.of("message", message);
    }

    // Handle POST request to save a new greeting message
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> saveGreeting(@RequestBody Map<String, String> payload) {
        String message = payload.get("message");
        if (message == null || message.trim().isEmpty()) {
            return Map.of("error", "Message content is required");
        }
        greetingService.saveGreeting(message);
        return Map.of("status", "Greeting saved successfully");
    }

    // Handle PUT request
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> putGreeting() {
        return Map.of("message", "Hello, PUT request!");
    }

    // Handle DELETE request
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> deleteGreeting() {
        return Map.of("message", "Hello, DELETE request!");
    }

    // Get default greeting message
    @GetMapping("/default")
    public Map<String, String> getDefaultGreeting() {
        String defaultGreeting = greetingService.getDefaultGreeting();
        return Map.of("message", defaultGreeting);
    }

    // Get all saved greetings
    @GetMapping("/all")
    public List<Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }
}