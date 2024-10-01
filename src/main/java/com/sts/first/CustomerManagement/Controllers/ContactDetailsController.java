package com.sts.first.CustomerManagement.Controllers;

import com.sts.first.CustomerManagement.dtos.ApiResponseMessage;
import com.sts.first.CustomerManagement.dtos.ContactDetailsDto;
import com.sts.first.CustomerManagement.exceptions.FileNotFoundCustomException;
import com.sts.first.CustomerManagement.repositories.ContactDetailsRepository;
import com.sts.first.CustomerManagement.services.ContactDetailsService;
import com.sts.first.CustomerManagement.services.FileService;
import com.sts.first.CustomerManagement.validate.CreateValidation;
import com.sts.first.CustomerManagement.validate.UpdateValidation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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
@RequestMapping("/contacts")
public class ContactDetailsController {

    @Autowired
    private ContactDetailsService contactDetailsService;

    @Autowired
    private FileService fileService;

    @PostMapping("/create")
    public ResponseEntity<ContactDetailsDto> createContact( @Validated(CreateValidation.class) @RequestBody ContactDetailsDto contactDetailsDto) {
        ContactDetailsDto createdContact = contactDetailsService.createContact(contactDetailsDto);
        return new ResponseEntity<>(createdContact, HttpStatus.CREATED);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<ContactDetailsDto> updateContact( @PathVariable Long contactId, @Validated(UpdateValidation.class) @RequestBody ContactDetailsDto contactDetailsDto) {
        ContactDetailsDto updatedContact = contactDetailsService.updateContact(contactId, contactDetailsDto);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<ApiResponseMessage> deleteContact(@PathVariable Long contactId) {
        contactDetailsService.deleteContact(contactId);
        ApiResponseMessage message= ApiResponseMessage.builder()
                .message("Successfully Deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<ContactDetailsDto> getContactById(@PathVariable Long contactId) {
        ContactDetailsDto contactDetailsDto = contactDetailsService.getContactById(contactId);
        return ResponseEntity.ok(contactDetailsDto);
    }

    @GetMapping("/allContacts")
    public ResponseEntity<List<ContactDetailsDto>> getAllContacts() {
        List<ContactDetailsDto> contacts = contactDetailsService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContactDetailsDto>> searchContacts(@RequestParam String query, @RequestParam String filterType) {
        List<ContactDetailsDto> contacts = contactDetailsService.searchContactsByKeyword(query, filterType);
        return ResponseEntity.ok(contacts);
    }


    @PostMapping("/image/{contactId}")
    public ResponseEntity<String> uploadContactImage(@PathVariable Long contactId, @RequestParam("file") MultipartFile file)  throws IOException, FileNotFoundCustomException {
        String imageUrl = fileService.uploadFile(file, "image");

        contactDetailsService.updateImageField(contactId, imageUrl);
         return ResponseEntity.ok("Image uploaded successfully: " + imageUrl);
    }

  @PostMapping("/resume/{contactId}")
  public ResponseEntity<String> uploadContactResume(
      @PathVariable Long contactId, @RequestParam("file") MultipartFile file)
      throws IOException, FileNotFoundCustomException {
        String resumeUrl = fileService.uploadFile(file, "resume");
        contactDetailsService.updateResumeField(contactId, resumeUrl);

         return ResponseEntity.ok(" Resume uploaded successfully: " + resumeUrl);
    }

  @GetMapping("/image/{contactId}")
  public void getContactImage(@PathVariable Long contactId, HttpServletResponse response)
      throws IOException, FileNotFoundCustomException {
        String imageUrl = contactDetailsService.getContactById(contactId).getImage();

        InputStream fileStream = fileService.getResource("image", imageUrl);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(fileStream, response.getOutputStream());
    }

//  @GetMapping("/resume/{contactId}")
//  public void getContactResume(@PathVariable Long contactId, HttpServletResponse response)
//      throws IOException, FileNotFoundCustomException {
//        String resumeUrl = contactDetailsService.getContactById(contactId).getResume();
//        InputStream fileStream = fileService.getResource("resume", resumeUrl);
//        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
//        StreamUtils.copy(fileStream, response.getOutputStream());
//    }

    @GetMapping("/resume/{contactId}")
    public void getContactResume(@PathVariable Long contactId, HttpServletResponse response)
            throws IOException, FileNotFoundCustomException {
        // Get resume URL from contact details service
        String resumeUrl = contactDetailsService.getContactById(contactId).getResume();

        // Get InputStream of the resume file
        InputStream fileStream = fileService.getResource("resume", resumeUrl);

        // Determine the content type based on file extension
        String contentType = determineContentType(resumeUrl);
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "inline; filename=\"" + resumeUrl + "\""); // Set file to open inline or downloaded

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