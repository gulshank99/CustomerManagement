//package com.sts.first.CustomerManagement.services.Impl;
//import com.cloudinary.Cloudinary;
//import com.cloudinary.utils.ObjectUtils;
//import com.sts.first.CustomerManagement.entities.ContactDetails;
//import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
//import com.sts.first.CustomerManagement.repositories.ContactDetailsRepository;
//import com.sts.first.CustomerManagement.services.CloudinaryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.Map;
//
//@Service
//public class CloudinaryServiceImpl implements CloudinaryService {
//
//    @Autowired
//    private Cloudinary cloudinary;
//    @Autowired
//    private ContactDetailsRepository contactDetailsRepository;
//
//    public String uploadFile(Long contactId, MultipartFile file) throws IOException {
//
////        String contentType = file.getContentType();
//
//        // Set resource type to "raw" for non-image files like PDF, DOC, DOCX
////        String resourceType = contentType != null && contentType.startsWith("image") ? "image" : "raw";
//
////        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(),
////                ObjectUtils.asMap("resource_type", resourceType));
//
//
//        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(),
//                ObjectUtils.asMap("resource_type", "auto"));
//
//        ContactDetails contactDetails = contactDetailsRepository.findById(contactId)
//                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + contactId));
//
//
//        String secureUrl = uploadResult.get("secure_url").toString();
//        contactDetails.setImage(secureUrl);
//        contactDetailsRepository.save(contactDetails);
//
//        return secureUrl;
//    }
//
//
//}
