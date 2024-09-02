package com.sts.first.CustomerManagement.services;

import com.sts.first.CustomerManagement.dtos.SearchCriteriaDto;
import com.sts.first.CustomerManagement.dtos.CandidateDetailDto;

import java.util.List;

public interface CandidateSearchService {
    List<CandidateDetailDto> searchCandidates(SearchCriteriaDto searchCriteriaDto);
}
