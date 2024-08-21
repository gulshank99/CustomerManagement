package com.sts.first.CustomerManagement.Controllers;
import com.sts.first.CustomerManagement.dtos.ContactPreferredLocationDto;
import com.sts.first.CustomerManagement.services.ContactPreferredLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-preferred-locations")
public class ContactPreferredLocationController {

    @Autowired
    private ContactPreferredLocationService contactPreferredLocationService;

    @PostMapping
    public ResponseEntity<ContactPreferredLocationDto> createContactPreferredLocation(@RequestBody ContactPreferredLocationDto contactPreferredLocationDto) {
        return ResponseEntity.ok(contactPreferredLocationService.createPreferredLocation(contactPreferredLocationDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactPreferredLocationDto> updateContactPreferredLocation(@PathVariable Long id, @RequestBody ContactPreferredLocationDto contactPreferredLocationDto) {
        return ResponseEntity.ok(contactPreferredLocationService.updatePreferredLocation(id, contactPreferredLocationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactPreferredLocation(@PathVariable Long id) {
        contactPreferredLocationService.deletePreferredLocation(id);
        return ResponseEntity.noContent().build();
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
