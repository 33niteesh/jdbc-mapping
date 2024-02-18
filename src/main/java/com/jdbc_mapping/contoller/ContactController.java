package com.jdbc_mapping.contoller;


import com.jdbc_mapping.model.Contact;
import com.jdbc_mapping.repository.ContactRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2")
@Tag(name="Contact Api")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contact")
    @Operation(summary="get all contacts")
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    @Operation(summary="get one contact")
    @GetMapping("/contact/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Contact contact = contactRepository.findById(id);
        if (contact == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contact);
    }

    @PostMapping("/addcontact")
    @Operation(summary="add one contact")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        int rows = contactRepository.save(contact);
        if (rows == 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(contact);
    }

    @Operation(summary="update one contact")
    @PutMapping("/editcontact/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        Contact existingContact = contactRepository.findById(id);
        if (existingContact == null) {
            return ResponseEntity.notFound().build();
        }
        contact.setCntcid(id);
        int rows = contactRepository.update(contact);
        if (rows == 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(contact);
    }

    @DeleteMapping("/remove/{id}")
    @Operation(summary="delete one contact")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        Contact contact = contactRepository.findById(id);
        if (contact == null) {
            return ResponseEntity.notFound().build();
        }
        contactRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
