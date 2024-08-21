package com.sts.first.CustomerManagement.Controllers;

import com.sts.first.CustomerManagement.dtos.ContactTechnologyDto;
import com.sts.first.CustomerManagement.services.ContactTechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-technology")
public class ContactTechnologyController {

    @Autowired
    private ContactTechnologyService contactsTechnologyService;

    @PostMapping
    public ResponseEntity<ContactTechnologyDto> createContactTechnology(@RequestBody ContactTechnologyDto contactTechnologyDto) {
        return ResponseEntity.ok(contactsTechnologyService.createContactTechnology(contactTechnologyDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactTechnologyDto> updateContactTechnology(@PathVariable Long id, @RequestBody ContactTechnologyDto contactsTechnologyDto) {
        return ResponseEntity.ok(contactsTechnologyService.updateContactTechnology(id, contactsTechnologyDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactTechnology(@PathVariable Long id) {
        contactsTechnologyService.deleteContactTechnology(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactTechnologyDto> getContactTechnologyById(@PathVariable Long id) {
        return ResponseEntity.ok(contactsTechnologyService.getContactTechnologyById(id));
    }

    @GetMapping
    public ResponseEntity<List<ContactTechnologyDto>> getAllContactTechnology() {
        return ResponseEntity.ok(contactsTechnologyService.getAllContactTechnologies());
    }
}
