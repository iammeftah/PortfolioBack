package com.example.portfolioback.controllers;

import com.example.portfolioback.models.Contact;
import com.example.portfolioback.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "http://localhost:3000") // Adjust this to match your frontend URL
public class ContactController {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveContact(@RequestBody Contact contact) {
        Map<String, String> response = new HashMap<>();

        try {
            System.out.println("Received contact: " + contact);

            // Validate input
            if (contact.getName() == null || contact.getName().trim().isEmpty() ||
                    contact.getEmail() == null || contact.getEmail().trim().isEmpty() ||
                    contact.getMessage() == null || contact.getMessage().trim().isEmpty()) {
                response.put("error", "Name, email, and message are required fields.");
                return ResponseEntity.badRequest().body(response);
            }

            Contact savedContact = contactRepository.save(contact);

            System.out.println("Contact saved");
            System.out.println("Email: " + savedContact.getEmail());
            System.out.println("Name: " + savedContact.getName());
            System.out.println("Message: " + savedContact.getMessage());

            response.put("message", "Contact saved successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("Error saving contact: " + e.getMessage());
            e.printStackTrace();
            response.put("error", "An error occurred while saving the contact.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}