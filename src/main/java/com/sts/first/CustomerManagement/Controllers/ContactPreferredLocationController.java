package com.sts.first.CustomerManagement.Controllers;
import com.sts.first.CustomerManagement.dtos.ApiResponseMessage;
import com.sts.first.CustomerManagement.dtos.ContactPreferredLocationDto;
import com.sts.first.CustomerManagement.services.ContactPreferredLocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-preferred-locations")
public class ContactPreferredLocationController {

    @Autowired
    private ContactPreferredLocationService contactPreferredLocationService;

    @PostMapping
    public ResponseEntity<ContactPreferredLocationDto> createContactPreferredLocation(@Valid @RequestBody ContactPreferredLocationDto contactPreferredLocationDto) {
        return ResponseEntity.ok(contactPreferredLocationService.createPreferredLocation(contactPreferredLocationDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactPreferredLocationDto> updateContactPreferredLocation(@PathVariable Long id, @RequestBody ContactPreferredLocationDto contactPreferredLocationDto) {
        return ResponseEntity.ok(contactPreferredLocationService.updatePreferredLocation(id, contactPreferredLocationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> deleteContactPreferredLocation(@PathVariable Long id) {
        contactPreferredLocationService.deletePreferredLocation(id); ApiResponseMessage message= ApiResponseMessage.builder()
                .message("Successfully Deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactPreferredLocationDto> getContactPreferredLocationById(@PathVariable Long id) {
        return ResponseEntity.ok(contactPreferredLocationService.getPreferredLocationById(id));
    }

    @GetMapping
    public ResponseEntity<List<ContactPreferredLocationDto>> getAllContactPreferredLocations() {
        return ResponseEntity.ok(contactPreferredLocationService.getAllPreferredLocations());
    }
}
