package com.sts.first.CustomerManagement.services;

import com.sts.first.CustomerManagement.dtos.InterviewRoundDto;

import java.util.List;

public interface InterviewRoundService {
    InterviewRoundDto createInterviewRound(InterviewRoundDto interviewRoundDto);
    InterviewRoundDto updateInterviewRound(Long id, InterviewRoundDto interviewRoundDto);
    void deleteInterviewRound(Long id);
    InterviewRoundDto getInterviewRoundById(Long id);
    List<InterviewRoundDto> getAllInterviewRounds();
}
