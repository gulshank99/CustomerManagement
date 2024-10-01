//package com.sts.first.CustomerManagement.Controllers;
//import com.sts.first.CustomerManagement.services.CloudinaryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/api/files")
//public class FileUploadController {
//
//    @Autowired
//    private CloudinaryService cloudinaryService;
//
//    @PostMapping("/upload/{contactId}")
//    public ResponseEntity<String> uploadFile(@PathVariable Long contactId, @RequestParam("file") MultipartFile file) {
//        try {
//            String url = cloudinaryService.uploadFile(contactId, file);
//
//            return ResponseEntity.ok(url);
//        } catch (IOException e) {
//            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
//        }
//    }
//}
