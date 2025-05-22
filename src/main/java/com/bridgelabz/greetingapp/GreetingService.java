package com.bridgelabz.greetingapp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingService {
	    public static String getGreetingMessage() {
	        return "Hello World";
	    }
	    
	    public static String getGreeting(String firstName, String lastName) {
	    	if (firstName != null && lastName != null) {
	            return "Hello " + firstName + " " + lastName;
	        } else if (firstName != null) {
	            return "Hello " + firstName;
	        } else if (lastName != null) {
	            return "Hello " + lastName;
	        } else {
	        	return getGreetingMessage();
	        }
	    }
	    
	    private final GreetingRepository greetingRepository;
	    public GreetingService(GreetingRepository greetingRepository) {
	        this.greetingRepository = greetingRepository;
	    }

	    // Return a default greeting message
	    public String getDefaultGreeting() {
	        return "Hello World";
	    }

	    // Save a custom greeting message
	    public Greeting saveGreeting(String message) {
	        Greeting greeting = new Greeting(message);
	        return greetingRepository.save(greeting);
	    }

	    // Retrieve all saved greetings
	    public List<Greeting> getAllGreetings() {
	        return greetingRepository.findAll();
	    }
	}