package com.sts.first.CustomerManagement.Controllers;


import com.sts.first.CustomerManagement.dtos.ContactDomainsDto;
import com.sts.first.CustomerManagement.services.ContactDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-domains")
public class ContactDomainsController {

    @Autowired
    private ContactDomainService contactDomainsService;

    @PostMapping
    public ResponseEntity<ContactDomainsDto> createContactDomain(@RequestBody ContactDomainsDto contactDomainsDto) {
        return ResponseEntity.ok(contactDomainsService.createContactDomain(contactDomainsDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDomainsDto> updateContactDomain(@PathVariable Long id, @RequestBody ContactDomainsDto contactDomainsDto) {
        return ResponseEntity.ok(contactDomainsService.updateContactDomain(id, contactDomainsDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactDomain(@PathVariable Long id) {
        contactDomainsService.deleteContactDomain(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDomainsDto> getContactDomainById(@PathVariable Long id) {
        return ResponseEntity.ok(contactDomainsService.getContactDomainById(id));
    }

//    @GetMapping
//    public ResponseEntity<List<ContactDomainsDto>> getAllContactDomains() {
//        return ResponseEntity.ok(contactDomainsService.getAllContactDomains());
//    }
}
