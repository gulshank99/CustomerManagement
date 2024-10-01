package com.sts.first.CustomerManagement.services;

import com.sts.first.CustomerManagement.dtos.ContactDetailsDto;
import com.sts.first.CustomerManagement.exceptions.FileNotFoundCustomException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ContactDetailsService {


    ContactDetailsDto createContact(ContactDetailsDto contactDetailsDto);

    ContactDetailsDto updateContact(Long contactId, ContactDetailsDto contactDetailsDto);

    void deleteContact(Long contactId);

    ContactDetailsDto getContactById(Long contactId);

    List<ContactDetailsDto> getAllContacts();

    List<ContactDetailsDto> searchContactsByKeyword(String query, String filterType);

  void updateResumeField(Long contactId, String value)
      throws IOException, FileNotFoundCustomException;

    void updateImageField(Long contactId, String value) throws IOException, FileNotFoundCustomException;
}
