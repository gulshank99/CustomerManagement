package com.sts.first.CustomerManagement.Controllers;


import com.sts.first.CustomerManagement.dtos.ApiResponseMessage;
import com.sts.first.CustomerManagement.dtos.ContactDomainsDto;
import com.sts.first.CustomerManagement.services.ContactDomainService;
import com.sts.first.CustomerManagement.validate.CreateValidation;
import com.sts.first.CustomerManagement.validate.UpdateValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-domains")
public class ContactDomainsController {

    @Autowired
    private ContactDomainService contactDomainsService;

    @PostMapping
    public ResponseEntity<ContactDomainsDto> createContactDomain(@Validated(CreateValidation.class) @RequestBody ContactDomainsDto contactDomainsDto) {
        return ResponseEntity.ok(contactDomainsService.createContactDomain(contactDomainsDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDomainsDto> updateContactDomain(  @PathVariable Long id,@Validated(UpdateValidation.class) ContactDomainsDto contactDomainsDto) {
        return ResponseEntity.ok(contactDomainsService.updateContactDomain(id, contactDomainsDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> deleteContactDomain(@PathVariable Long id) {
        contactDomainsService.deleteContactDomain(id);
        ApiResponseMessage message= ApiResponseMessage.builder()
                .message("Successfully Deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDomainsDto> getContactDomainById(@PathVariable Long id) {
        return ResponseEntity.ok(contactDomainsService.getContactDomainById(id));
    }

    @GetMapping
    public ResponseEntity<List<ContactDomainsDto>> getAllContactDomains() {
        return ResponseEntity.ok(contactDomainsService.getAllContactDomains());
    }
}
