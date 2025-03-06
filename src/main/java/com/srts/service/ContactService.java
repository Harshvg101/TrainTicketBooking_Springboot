package com.srts.service;

import com.srts.entity.Contact;
import com.srts.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    /**
     * Submit an inquiry.
     */
    public Contact submitInquiry(Contact contact) {
        return contactRepository.save(contact);
    }

    /**
     * Retrieve all inquiries (for admin).
     */
    public List<Contact> getAllInquiries() {
        return contactRepository.findAll();
    }

    /**
     * Get inquiry by ID.
     */
    public Optional<Contact> getInquiryById(Long id) {
        return contactRepository.findById(id);
    }

    /**
     * Mark an inquiry as replied.
     */
    public Contact markAsReplied(Long id) {
        Optional<Contact> contactOpt = contactRepository.findById(id);
        if (contactOpt.isPresent()) {
            Contact contact = contactOpt.get();
            contact.setReplied(true);
            return contactRepository.save(contact);
        }
        throw new RuntimeException("Inquiry not found.");
    }
}
