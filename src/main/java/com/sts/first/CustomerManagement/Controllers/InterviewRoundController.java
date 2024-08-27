package com.sts.first.CustomerManagement.Controllers;
import com.sts.first.CustomerManagement.dtos.InterviewRoundDto;
import com.sts.first.CustomerManagement.services.Impl.InterviewRoundServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/interview-rounds")
public class InterviewRoundController {

    @Autowired
    private InterviewRoundServiceImpl interviewRoundService;

    @PostMapping
    public ResponseEntity<InterviewRoundDto> createInterviewRound(@RequestBody InterviewRoundDto interviewRoundDto) {
        return ResponseEntity.ok(interviewRoundService.createInterviewRound(interviewRoundDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InterviewRoundDto> updateInterviewRound(@PathVariable Long id, @RequestBody InterviewRoundDto interviewRoundDto) {
        return ResponseEntity.ok(interviewRoundService.updateInterviewRound(id, interviewRoundDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterviewRound(@PathVariable Long id) {
        interviewRoundService.deleteInterviewRound(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterviewRoundDto> getInterviewRoundById(@PathVariable Long id) {
        return ResponseEntity.ok(interviewRoundService.getInterviewRoundById(id));
    }

    @GetMapping
    public ResponseEntity<List<InterviewRoundDto>> getAllInterviewRounds() {
        return ResponseEntity.ok(interviewRoundService.getAllInterviewRounds());
    }
}