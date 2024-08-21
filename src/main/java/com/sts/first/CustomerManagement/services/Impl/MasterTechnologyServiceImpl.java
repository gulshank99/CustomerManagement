package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.dtos.MasterTechnologyDto;
import com.sts.first.CustomerManagement.entities.MasterTechnology;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.MasterTechnologyRepository;
import com.sts.first.CustomerManagement.services.MasterTechnologyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterTechnologyServiceImpl implements MasterTechnologyService {

    @Autowired
    private MasterTechnologyRepository masterTechnologyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MasterTechnologyDto createTechnology(MasterTechnologyDto technologyDto) {
        MasterTechnology masterTechnology = modelMapper.map(technologyDto, MasterTechnology.class);
        MasterTechnology savedTechnology = masterTechnologyRepository.save(masterTechnology);
        return modelMapper.map(savedTechnology, MasterTechnologyDto.class);
    }

    @Override
    public MasterTechnologyDto updateTechnology(Long techId, MasterTechnologyDto technologyDto) {
        MasterTechnology masterTechnology = masterTechnologyRepository.findById(techId)
                .orElseThrow(() -> new ResourceNotFoundException("Technology not found with id: " + techId));

        masterTechnology.setTechnology(technologyDto.getTechnology());
        MasterTechnology updatedTechnology = masterTechnologyRepository.save(masterTechnology);
        return modelMapper.map(updatedTechnology, MasterTechnologyDto.class);
    }

    @Override
    public void deleteTechnology(Long techId) {
        MasterTechnology masterTechnology = masterTechnologyRepository.findById(techId)
                .orElseThrow(() -> new ResourceNotFoundException("Technology not found with id: " + techId));
        masterTechnologyRepository.delete(masterTechnology);
    }

    @Override
    public MasterTechnologyDto getTechnologyById(Long techId) {
        MasterTechnology masterTechnology = masterTechnologyRepository.findById(techId)
                .orElseThrow(() -> new ResourceNotFoundException("Technology not found with id: " + techId));
        return modelMapper.map(masterTechnology, MasterTechnologyDto.class);
    }

    @Override
    public List<MasterTechnologyDto> getAllTechnologies() {
        return masterTechnologyRepository.findAll().stream()
                .map(technology -> modelMapper.map(technology, MasterTechnologyDto.class))
                .collect(Collectors.toList());
    }

}