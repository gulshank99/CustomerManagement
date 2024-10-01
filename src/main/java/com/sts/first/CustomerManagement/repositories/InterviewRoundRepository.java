package com.sts.first.CustomerManagement.repositories;

import com.sts.first.CustomerManagement.entities.InterviewRound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InterviewRoundRepository extends JpaRepository<InterviewRound,Long> {

    @Query("SELECT COALESCE(MAX(ir.roundId), 0) FROM InterviewRound ir")
    Long findMaxId();

    Optional<InterviewRound> findByInterviewInterviewIdAndRoundNumber(Long interviewId, Integer roundNumber);
}
