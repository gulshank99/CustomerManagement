package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.dtos.*;
import com.sts.first.CustomerManagement.entities.*;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.*;
import com.sts.first.CustomerManagement.services.ClientJobTechService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientJobTechServiceImpl implements ClientJobTechService {

    @Autowired
    private ClientJobTechRepository clientJobTechRepository;

    @Autowired
    private MasterDomainRepository masterDomainRepository;

    @Autowired
    private MasterTechnologyRepository masterTechnologyRepository;

    @Autowired
    private ClientJobRepository clientJobRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ClientJobTechDto createJobTech(ClientJobTechDto jobTechDto) {
        validateTechIdAndDomainId(jobTechDto);
        Long newId = clientJobTechRepository.findMaxId() + 1;
        jobTechDto.setJobCodeTechId(newId);

        Optional<ClientJobTech> existingTech =
                clientJobTechRepository.findByJob_JobIdAndTechnology_TechId(jobTechDto.getJob().getJobId(), jobTechDto.getTechnology().getTechId());

        if (existingTech.isPresent()) {
            throw new IllegalArgumentException("The combination of Job ID and Tech ID already exists.");
        }

        ClientJobTech jobTech = modelMapper.map(jobTechDto, ClientJobTech.class);
        ClientJobTech savedJobTech = clientJobTechRepository.save(jobTech);
        return modelMapper.map(savedJobTech, ClientJobTechDto.class);
    }

    @Override
    public ClientJobTechDto updateJobTech(Long jobTechId, ClientJobTechDto jobTechDto) {
        ClientJobTech jobTech = clientJobTechRepository.findById(jobTechId)
                .orElseThrow(() -> new ResourceNotFoundException("Job Tech not found with id: " + jobTechId));

        Optional<ClientJobTech> existingTech =
                clientJobTechRepository.findByJob_JobIdAndTechnology_TechId(jobTechDto.getJob().getJobId(), jobTechDto.getTechnology().getTechId());

        if (existingTech.isPresent()) {
            throw new IllegalArgumentException("The combination of Job ID and Tech ID already exists.");
        }

        if (jobTechDto.getTechnology() != null) {
            jobTech.setTechnology(modelMapper.map(jobTechDto.getTechnology(), MasterTechnology.class));
        }
        if (jobTechDto.getDomain() != null) {
            jobTech.setDomain(modelMapper.map(jobTechDto.getDomain(), MasterDomain.class));
        }

        if (jobTechDto.getDomain() != null && jobTechDto.getDomain().getDomainId() != null) {
            MasterDomain masterDomain = masterDomainRepository.findById(jobTechDto.getDomain().getDomainId())
                    .orElseThrow(() -> new ResourceNotFoundException("Domain ID not found with id: " + jobTechDto.getDomain().getDomainId()));
            jobTech.setDomain(masterDomain);
        }

        if (jobTechDto.getTechnology() != null && jobTechDto.getTechnology().getTechId() != null) {
            MasterTechnology masterTechnology = masterTechnologyRepository.findById(jobTechDto.getTechnology().getTechId())
                    .orElseThrow(() -> new ResourceNotFoundException("Technology ID not found with id: " + jobTechDto.getTechnology().getTechId()));
            jobTech.setTechnology(masterTechnology);
        }

         if(jobTechDto.getJob() != null && jobTechDto.getJob().getJobId() != null) {
            ClientJob clientJob = clientJobRepository.findById(jobTechDto.getJob().getJobId())
                    .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + jobTechDto.getJob().getJobId()));
            jobTech.setJob(clientJob);
        }


        ClientJobTech updatedJobTech = clientJobTechRepository.save(jobTech);
        return modelMapper.map(updatedJobTech, ClientJobTechDto.class);
    }

    @Override
    public void deleteJobTech(Long jobTechId) {
        ClientJobTech jobTech = clientJobTechRepository.findById(jobTechId)
                .orElseThrow(() -> new ResourceNotFoundException("Job Tech not found with id: " + jobTechId));
        clientJobTechRepository.delete(jobTech);
    }

    @Override
    public ClientJobTechDto getJobTechById(Long jobTechId) {
        ClientJobTech jobTech = clientJobTechRepository.findById(jobTechId)
                .orElseThrow(() -> new ResourceNotFoundException("Job Tech not found with id: " + jobTechId));
        return modelMapper.map(jobTech, ClientJobTechDto.class);
    }

    @Override
    public List<ClientJobTechDto> getAllJobTechs() {
        return clientJobTechRepository.findAll().stream()
                .map(jobTech -> modelMapper.map(jobTech, ClientJobTechDto.class))
                .collect(Collectors.toList());
    }

    private void validateTechIdAndDomainId(ClientJobTechDto jobTechDto  )  {
         MasterDomainDto domain = jobTechDto.getDomain();
         MasterTechnologyDto technology = jobTechDto.getTechnology();
        ClientJobDto job = jobTechDto.getJob();


        if (domain == null || domain.getDomainId() == null) {
            throw new IllegalArgumentException("Domain ID must be present in the Domain!");
        }

        if (technology == null || technology.getTechId() == null) {
            throw new IllegalArgumentException("Tech ID must be present in the Location!");
        }

        if (job == null || job.getJobId() == null) {
            throw new IllegalArgumentException("Job ID must be present in the Job!");
        }

        if (jobTechDto.getTechnology().getTechId() != null) {
            masterTechnologyRepository.findById(jobTechDto.getTechnology().getTechId())
                    .orElseThrow(() -> new ResourceNotFoundException("Technology not found with id: " +  jobTechDto.getTechnology().getTechId()));
        }

        if (jobTechDto.getDomain().getDomainId() != null) {
            masterDomainRepository.findById(jobTechDto.getDomain().getDomainId())
                    .orElseThrow(() -> new ResourceNotFoundException("Domain not found with id: " + jobTechDto.getDomain().getDomainId()));

        }

        if (jobTechDto.getJob().getJobId() != null) {
            clientJobRepository.findById(jobTechDto.getJob().getJobId())
                    .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + jobTechDto.getJob().getJobId()));
        }


    }
}
