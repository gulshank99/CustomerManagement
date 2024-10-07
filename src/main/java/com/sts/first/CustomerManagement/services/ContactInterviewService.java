package com.sts.first.CustomerManagement.services;

import com.sts.first.CustomerManagement.dtos.ContactInterviewsDto;
import com.sts.first.CustomerManagement.dtos.InterviewRoundDto;

import java.util.List;
public interface ContactInterviewService {

    ContactInterviewsDto createContactInterview(ContactInterviewsDto contactInterviewsDto);

    ContactInterviewsDto updateContactInterview(Long id, ContactInterviewsDto contactInterviewsDto);

    void deleteContactInterview(Long id);

    List<ContactInterviewsDto> getAllContactsByJobId(Long jobId);

    List<ContactInterviewsDto> getAllInterviewsByContactId(Long contactId);

    List<ContactInterviewsDto> getAllInterviewsByContactIdAndClientId(Long contactId, Long clientId);



    ContactInterviewsDto getContactInterviewById(Long id);

    List<ContactInterviewsDto> getAllContactInterviews();
}