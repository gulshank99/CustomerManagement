package com.sts.first.CustomerManagement.Controllers;

import com.sts.first.CustomerManagement.dtos.ApiResponseMessage;
import com.sts.first.CustomerManagement.dtos.ClientJobDomainDto;
import com.sts.first.CustomerManagement.dtos.ContactDomainsDto;
import com.sts.first.CustomerManagement.services.ClientJobDomainService;
import com.sts.first.CustomerManagement.services.ContactDomainService;
import com.sts.first.CustomerManagement.validate.CreateValidation;
import com.sts.first.CustomerManagement.validate.UpdateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client-job-domain")
public class ClientJobDomainController {
    @Autowired
    private ClientJobDomainService clientJobDomainService;

    @PostMapping
    public ResponseEntity<ClientJobDomainDto> createClientJobDomain(@Validated(CreateValidation.class) @RequestBody ClientJobDomainDto contactDomainsDto) {
        return ResponseEntity.ok(clientJobDomainService.createClientJobDomain(contactDomainsDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientJobDomainDto> updateClientJobDomain(  @PathVariable Long id,@Validated(UpdateValidation.class) ClientJobDomainDto contactDomainsDto) {
        return ResponseEntity.ok(clientJobDomainService.updateClientJobDomain(id, contactDomainsDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> deleteClientJobDomain(@PathVariable Long id) {
        clientJobDomainService.deleteClientJobDomain(id);
        ApiResponseMessage message= ApiResponseMessage.builder()
                .message("Successfully Deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientJobDomainDto> getClientJobDomainById (@PathVariable Long id) {
        return ResponseEntity.ok(clientJobDomainService.getClientJobDomainById(id));
    }

    @GetMapping
    public ResponseEntity<List<ClientJobDomainDto>> getAllClientJobDomains() {
        return ResponseEntity.ok(clientJobDomainService.getAllClientJobDomains());
    }

}
