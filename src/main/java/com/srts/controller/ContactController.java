package com.srts.controller;

import com.srts.entity.Contact;
import com.srts.service.ContactService;
import com.srts.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * API for users to submit an inquiry.
     */
    @PostMapping("/submit")
    public ResponseEntity<?> submitInquiry(@RequestHeader("Authorization") String token,
                                           @RequestBody Contact contact) {
        String userEmail = jwtUtil.extractUsername(token.substring(7));
        contact.setUserEmail(userEmail);
        Contact savedContact = contactService.submitInquiry(contact);
        return ResponseEntity.ok(savedContact);
    }

    /**
     * API for admin to get all inquiries.
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllInquiries() {
        List<Contact> inquiries = contactService.getAllInquiries();
        return ResponseEntity.ok(inquiries);
    }

    /**
     * API to get an inquiry by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getInquiryById(@PathVariable Long id) {
        Optional<Contact> contact = contactService.getInquiryById(id);
        return contact.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build()); //
    }


    /**
     * API for admin to mark an inquiry as replied.
     */
    @PutMapping("/reply/{id}")
    public ResponseEntity<?> markAsReplied(@PathVariable Long id) {
        Contact contact = contactService.markAsReplied(id);
        return ResponseEntity.ok(contact);
    }
}
