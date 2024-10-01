package com.sts.first.CustomerManagement.Controllers;

import com.sts.first.CustomerManagement.dtos.ClientJobDto;
import com.sts.first.CustomerManagement.exceptions.FileNotFoundCustomException;
import com.sts.first.CustomerManagement.services.ClientJobService;
import com.sts.first.CustomerManagement.services.FileService;
import com.sts.first.CustomerManagement.validate.CreateValidation;
import com.sts.first.CustomerManagement.validate.UpdateValidation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class ClientJobController {

    @Autowired
    private ClientJobService clientJobService;

    @Autowired
    private FileService fileService;

    Logger logger = LoggerFactory.getLogger(ClientJobController.class);

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

    @PostMapping("/jd/{jobId}")
    public ResponseEntity<String> uploadJd(
            @PathVariable Long jobId, @RequestParam("file") MultipartFile file)
            throws IOException, FileNotFoundCustomException {
        String jobDescription = fileService.uploadFile(file, "jd");
        clientJobService.updateJdField(jobId,jobDescription);

        return ResponseEntity.ok(" Job Description uploaded successfully: " + jobDescription);
    }

    @GetMapping("/jd/{jobId}")
    public void getJD(@PathVariable Long jobId, HttpServletResponse response)
            throws IOException, FileNotFoundCustomException {
        // Get resume URL from contact details service
        String jobDescription = clientJobService.getJobById(jobId).getJd();
        logger.info("JD URL: " + jobDescription);
        // Get InputStream of the resume file
        InputStream fileStream = fileService.getResource("jd",  jobDescription);
         logger.info("JD InputStream: " + fileStream);

        // Determine the content type based on file extension
        String contentType = determineContentType(jobDescription);
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "inline; filename=\"" + jobDescription  + "\""); // Set file to open inline or downloaded

        // Write file stream to response output
        StreamUtils.copy(fileStream, response.getOutputStream());
        response.flushBuffer();
    }

    /**
     * Determines the content type based on the file extension.
     */
    private String determineContentType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        switch (extension) {
            case ".pdf":
                return "application/pdf";
            case ".doc":
                return "application/msword";
            case ".docx":
                return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            default:
                return MediaType.APPLICATION_OCTET_STREAM_VALUE; // Generic binary stream if unknown type
        }
    }
}
