package com.sts.first.CustomerManagement.Controllers;
import com.sts.first.CustomerManagement.dtos.ApiResponseMessage;
import com.sts.first.CustomerManagement.dtos.InterviewRoundDto;
import com.sts.first.CustomerManagement.services.Impl.InterviewRoundServiceImpl;
import com.sts.first.CustomerManagement.validate.CreateValidation;
import com.sts.first.CustomerManagement.validate.UpdateValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/interview-rounds")
public class InterviewRoundController {

    @Autowired
    private InterviewRoundServiceImpl interviewRoundService;

    @PostMapping
    public ResponseEntity<InterviewRoundDto> createInterviewRound( @Validated(CreateValidation.class) @RequestBody InterviewRoundDto interviewRoundDto) {
        return ResponseEntity.ok(interviewRoundService.createInterviewRound(interviewRoundDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InterviewRoundDto> updateInterviewRound(@PathVariable Long id,  @Validated(UpdateValidation.class) @RequestBody InterviewRoundDto interviewRoundDto) {
        return ResponseEntity.ok(interviewRoundService.updateInterviewRound(id, interviewRoundDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> deleteInterviewRound(@PathVariable Long id) {
        interviewRoundService.deleteInterviewRound(id); ApiResponseMessage message= ApiResponseMessage.builder()
                .message("Successfully Deleted")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterviewRoundDto> getInterviewRoundById(@PathVariable Long id) {
        return ResponseEntity.ok(interviewRoundService.getInterviewRoundById(id));
    }

    @GetMapping
    public ResponseEntity<List<InterviewRoundDto>> getAllInterviewRounds() {
        return ResponseEntity.ok(interviewRoundService.getAllInterviewRounds());
    }

    @GetMapping("/contact/{contactId}")
    public ResponseEntity<List<InterviewRoundDto>> getInterviewRoundsByContactId(@PathVariable Long contactId) {
        List<InterviewRoundDto> interviewRounds = interviewRoundService.getAllInterviewRoundsByContactId(contactId);
        return ResponseEntity.ok(interviewRounds);
    }

    @GetMapping("/contact/{contactId}/job/{jobId}")
    public ResponseEntity<List<InterviewRoundDto>> getInterviewRoundsByContactIdAndJobId(
            @PathVariable Long contactId, @PathVariable Long jobId) {
        List<InterviewRoundDto> interviewRounds = interviewRoundService.getAllInterviewRoundsByContactIdAndJobId(contactId, jobId);
        return ResponseEntity.ok(interviewRounds);
    }

}