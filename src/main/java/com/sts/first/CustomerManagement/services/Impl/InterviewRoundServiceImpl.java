package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.dtos.ContactInterviewsDto;
import com.sts.first.CustomerManagement.dtos.InterviewRoundDto;
import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.ContactInterviews;
import com.sts.first.CustomerManagement.entities.InterviewRound;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.ContactDetailsRepository;
import com.sts.first.CustomerManagement.repositories.ContactInterviewRepository;
import com.sts.first.CustomerManagement.repositories.InterviewRoundRepository;
import com.sts.first.CustomerManagement.services.InterviewRoundService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InterviewRoundServiceImpl implements InterviewRoundService {

    @Autowired
    private InterviewRoundRepository interviewRoundRepository;
    @Autowired
    private ContactInterviewRepository contactInterviewRepository;

    @Autowired
    private ContactDetailsRepository contactDetailsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public InterviewRoundDto createInterviewRound(InterviewRoundDto interviewRoundDto) {

        validateInterviewId(interviewRoundDto);

        Long maxId = interviewRoundRepository.findMaxId();
        Long newId = maxId + 1;
        interviewRoundDto.setRoundId(newId);

        Long interviewId = interviewRoundDto.getInterview().getInterviewId();
        Integer roundNumber = interviewRoundDto.getRoundNumber();

        // Check if a combination of interviewId and roundNumber already exists
        Optional<InterviewRound> existingInterviewRound =
                interviewRoundRepository.findByInterviewInterviewIdAndRoundNumber(interviewId, roundNumber);

        if (existingInterviewRound.isPresent()) {
            throw new IllegalArgumentException("The combination of interview ID and round number already exists.");
        }

        InterviewRound interviewRound = modelMapper.map(interviewRoundDto, InterviewRound.class);
        InterviewRound savedInterviewRound = interviewRoundRepository.save(interviewRound);

        return modelMapper.map(savedInterviewRound, InterviewRoundDto.class);
    }

    @Override
    public InterviewRoundDto updateInterviewRound(Long id, InterviewRoundDto interviewRoundDto) {
        InterviewRound interviewRound = interviewRoundRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interview round not found with id: " + id));

        // Update the fields in the entity with values from the DTO
        Long interviewId = interviewRoundDto.getInterview().getInterviewId();
        Integer roundNumber = interviewRoundDto.getRoundNumber();

        // Check if a combination of interviewId and roundNumber already exists
        Optional<InterviewRound> existingInterviewRound =
                interviewRoundRepository.findByInterviewInterviewIdAndRoundNumber(interviewId, roundNumber);

        if (existingInterviewRound.isPresent()) {
            throw new IllegalArgumentException("The combination of interview ID and round number already exists.");
        }

        if (interviewRoundDto.getRoundNumber() != null) {
            interviewRound.setRoundNumber(interviewRoundDto.getRoundNumber());
        }
        if (interviewRoundDto.getRoundDate() != null) {
            interviewRound.setRoundDate(interviewRoundDto.getRoundDate());
        }
        if (interviewRoundDto.getInterviewerName() != null) {
            interviewRound.setInterviewerName(interviewRoundDto.getInterviewerName());
        }
        if (interviewRoundDto.getTechnologyInterviewed() != null) {
            interviewRound.setTechnologyInterviewed(interviewRoundDto.getTechnologyInterviewed());
        }
        if (interviewRoundDto.getTechRating() != null) {
            interviewRound.setTechRating(interviewRoundDto.getTechRating());
        }
        if (interviewRoundDto.getSoftskillsRating() != null) {
            interviewRound.setSoftskillsRating(interviewRoundDto.getSoftskillsRating());
        }
        if (interviewRoundDto.getInterviewStatus() != null) {
            interviewRound.setInterviewStatus(interviewRoundDto.getInterviewStatus());
        }

        if (interviewRoundDto.getRemarks() != null) {
            interviewRound.setRemarks(interviewRoundDto.getRemarks());
        }

//        validateInterviewId(interviewRoundDto);

        // Fetch and set related entities
        if (interviewRoundDto.getInterview() != null && interviewRoundDto.getInterview().getInterviewId() != null) {
            ContactInterviews contactInterviews = contactInterviewRepository.findById(interviewRoundDto.getInterview().getInterviewId())
                    .orElseThrow(() -> new ResourceNotFoundException("Interview not found with id: " + interviewRoundDto.getInterview().getInterviewId()));
            interviewRound.setInterview(contactInterviews);
        }

//        if (interviewRoundDto.getContactDetails() != null) {
//            ContactDetails contactDetails = contactDetailsRepository.findById(interviewRoundDto.getContactDetails().getContactId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + interviewRoundDto.getContactDetails().getContactId()));
//            interviewRound.setContactDetails(contactDetails);
//        }

        InterviewRound updatedInterviewRound = interviewRoundRepository.save(interviewRound);
        return modelMapper.map(updatedInterviewRound, InterviewRoundDto.class);
    }

    @Override
    public void deleteInterviewRound(Long id) {
        InterviewRound interviewRound = interviewRoundRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interview round not found with id: " + id));
        interviewRoundRepository.delete(interviewRound);
    }

    @Override
    public InterviewRoundDto getInterviewRoundById(Long id) {
        InterviewRound interviewRound = interviewRoundRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Interview round not found with id: " + id));
        return modelMapper.map(interviewRound, InterviewRoundDto.class);
    }

    @Override
    public List<InterviewRoundDto> getAllInterviewRounds() {
        return interviewRoundRepository.findAll().stream()
                .map(interviewRound -> modelMapper.map(interviewRound, InterviewRoundDto.class))
                .collect(Collectors.toList());
    }


    /**
     * Validates that the interview id is present in the interview details.
     * If the interview id is present, it checks if the interview exists in the database.
     * If the interview does not exist, it throws a ResourceNotFoundException.
     */
    private void validateInterviewId(InterviewRoundDto interviewRoundDto) {
        ContactInterviewsDto contactInterviewsDto = interviewRoundDto.getInterview();

        if (contactInterviewsDto == null || contactInterviewsDto.getInterviewId() == null) {
            throw new IllegalArgumentException("Interview ID must be present in the interview details!");
        }

        if (interviewRoundDto.getInterview().getInterviewId() != null) {
              contactInterviewRepository.findById(interviewRoundDto.getInterview().getInterviewId())
                    .orElseThrow(() -> new ResourceNotFoundException("Interview not found with id: " + interviewRoundDto.getInterview().getInterviewId()));

        }
    }





}