package com.sts.first.CustomerManagement.services;

import com.sts.first.CustomerManagement.dtos.MasterTechnologyDto;

import java.util.List;

public interface MasterTechnologyService {
    MasterTechnologyDto createTechnology(MasterTechnologyDto technologyDto);
    MasterTechnologyDto updateTechnology(Long techId, MasterTechnologyDto technologyDto);
    void deleteTechnology(Long techId);
    MasterTechnologyDto getTechnologyById(Long techId);
    List<MasterTechnologyDto> getAllTechnologies();
}
