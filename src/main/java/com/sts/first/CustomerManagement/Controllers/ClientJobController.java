package com.sts.first.CustomerManagement.Controllers;

import com.sts.first.CustomerManagement.dtos.ClientJobDto;
import com.sts.first.CustomerManagement.services.ClientJobService;
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
@RequestMapping("/api/jobs")
public class ClientJobController {

    @Autowired
    private ClientJobService clientJobService;

    @PostMapping
    public ResponseEntity<ClientJobDto> createJob(@Validated(CreateValidation.class) @RequestBody   ClientJobDto jobDto) {
        ClientJobDto createdJob = clientJobService.createJob(jobDto);
        return new ResponseEntity<>(createdJob, HttpStatus.CREATED);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<ClientJobDto> updateJob(
            @PathVariable Long jobId,
            @Validated(UpdateValidation.class) @RequestBody ClientJobDto jobDto) {
        ClientJobDto updatedJob = clientJobService.updateJob(jobId, jobDto);
        return ResponseEntity.ok(updatedJob);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long jobId) {
        clientJobService.deleteJob(jobId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<ClientJobDto> getJobById(@PathVariable Long jobId) {
        ClientJobDto jobDto = clientJobService.getJobById(jobId);
        return ResponseEntity.ok(jobDto);
    }

    @GetMapping
    public ResponseEntity<List<ClientJobDto>> getAllJobs() {
        List<ClientJobDto> jobs = clientJobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }
}
