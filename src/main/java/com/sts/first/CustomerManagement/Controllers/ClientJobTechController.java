package com.sts.first.CustomerManagement.Controllers;

import com.sts.first.CustomerManagement.dtos.ClientJobTechDto;
import com.sts.first.CustomerManagement.services.ClientJobTechService;
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
@RequestMapping("/api/job-tech")
public class ClientJobTechController {

    @Autowired
    private ClientJobTechService clientJobTechService;

    @PostMapping
    public ResponseEntity<ClientJobTechDto> createJobTech(@Validated(CreateValidation.class) @RequestBody  ClientJobTechDto jobTechDto) {
        ClientJobTechDto createdJobTech = clientJobTechService.createJobTech(jobTechDto);
        return new ResponseEntity<>(createdJobTech, HttpStatus.CREATED);
    }

    @PutMapping("/{jobTechId}")
    public ResponseEntity<ClientJobTechDto> updateJobTech(
            @PathVariable Long jobTechId,
            @Validated(UpdateValidation.class) @RequestBody ClientJobTechDto jobTechDto) {
        ClientJobTechDto updatedJobTech = clientJobTechService.updateJobTech(jobTechId, jobTechDto);
        return ResponseEntity.ok(updatedJobTech);
    }

    @DeleteMapping("/{jobTechId}")
    public ResponseEntity<Void> deleteJobTech(@PathVariable Long jobTechId) {
        clientJobTechService.deleteJobTech(jobTechId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{jobTechId}")
    public ResponseEntity<ClientJobTechDto> getJobTechById(@PathVariable Long jobTechId) {
        ClientJobTechDto jobTechDto = clientJobTechService.getJobTechById(jobTechId);
        return ResponseEntity.ok(jobTechDto);
    }

    @GetMapping
    public ResponseEntity<List<ClientJobTechDto>> getAllJobTechs() {
        List<ClientJobTechDto> jobTechs = clientJobTechService.getAllJobTechs();
        return ResponseEntity.ok(jobTechs);
    }
}
