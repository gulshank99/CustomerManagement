package com.sts.first.CustomerManagement.Controllers;

import com.sts.first.CustomerManagement.dtos.ApiResponseMessage;
import com.sts.first.CustomerManagement.dtos.ContactDetailsDto;
import com.sts.first.CustomerManagement.services.ContactDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactDetailsController {

    @Autowired
    private ContactDetailsService contactDetailsService;

    @PostMapping("/create")
    public ResponseEntity<ContactDetailsDto> createContact(@RequestBody ContactDetailsDto contactDetailsDto) {
        ContactDetailsDto createdContact = contactDetailsService.createContact(contactDetailsDto);
        return new ResponseEntity<>(createdContact, HttpStatus.CREATED);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<ContactDetailsDto> updateContact(@PathVariable Long contactId, @RequestBody ContactDetailsDto contactDetailsDto) {
        ContactDetailsDto updatedContact = contactDetailsService.updateContact(contactId, contactDetailsDto);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<ApiResponseMessage> deleteContact(@PathVariable Long contactId) {
        contactDetailsService.deleteContact(contactId);
        ApiResponseMessage message= ApiResponseMessage.builder()
                .message("Successfully Deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<ContactDetailsDto> getContactById(@PathVariable Long contactId) {
        ContactDetailsDto contactDetailsDto = contactDetailsService.getContactById(contactId);
        return ResponseEntity.ok(contactDetailsDto);
    }

    @GetMapping("/allContacts")
    public ResponseEntity<List<ContactDetailsDto>> getAllContacts() {
        List<ContactDetailsDto> contacts = contactDetailsService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContactDetailsDto>> searchContacts(@RequestParam String query, @RequestParam String filterType) {
        List<ContactDetailsDto> contacts = contactDetailsService.searchContactsByKeyword(query, filterType);
        return ResponseEntity.ok(contacts);
    }
}