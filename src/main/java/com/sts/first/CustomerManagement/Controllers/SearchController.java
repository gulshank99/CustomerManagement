package com.sts.first.CustomerManagement.Controllers;

import com.sts.first.CustomerManagement.dtos.CandidateDetailDto;
import com.sts.first.CustomerManagement.dtos.SearchCriteriaDto;
import com.sts.first.CustomerManagement.services.CandidateSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private CandidateSearchService candidateSearchService;

    @PostMapping("/candidates")
    public ResponseEntity<List<CandidateDetailDto>> searchCandidates(@RequestBody SearchCriteriaDto searchCriteriaDto) {
        List<CandidateDetailDto> candidates = candidateSearchService.searchCandidates(searchCriteriaDto);
        return ResponseEntity.ok(candidates);
    }
}
