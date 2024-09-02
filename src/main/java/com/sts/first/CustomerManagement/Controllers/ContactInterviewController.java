package com.sts.first.CustomerManagement.Controllers;
import com.sts.first.CustomerManagement.dtos.ApiResponseMessage;
import com.sts.first.CustomerManagement.dtos.ContactInterviewsDto;
import com.sts.first.CustomerManagement.services.ContactInterviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact-interviews")
public class ContactInterviewController {

    @Autowired
    private ContactInterviewService contactInterviewsService;

    @PostMapping
    public ResponseEntity<ContactInterviewsDto> createContactInterview(@Valid @RequestBody ContactInterviewsDto contactInterviewsDto) {
        return ResponseEntity.ok(contactInterviewsService.createContactInterview(contactInterviewsDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactInterviewsDto> updateContactInterview(@PathVariable Long id, @RequestBody ContactInterviewsDto contactInterviewsDto) {
        return ResponseEntity.ok(contactInterviewsService.updateContactInterview(id, contactInterviewsDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> deleteContactInterview(@PathVariable Long id) {
        contactInterviewsService.deleteContactInterview(id); ApiResponseMessage message= ApiResponseMessage.builder()
                .message("Successfully Deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactInterviewsDto> getContactInterviewById(@PathVariable Long id) {
        return ResponseEntity.ok(contactInterviewsService.getContactInterviewById(id));
    }

    @GetMapping
    public ResponseEntity<List<ContactInterviewsDto>> getAllContactInterviews() {
        return ResponseEntity.ok(contactInterviewsService.getAllContactInterviews());
    }
}
