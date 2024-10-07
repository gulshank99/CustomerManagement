package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.dtos.*;
import com.sts.first.CustomerManagement.entities.*;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.*;
import com.sts.first.CustomerManagement.services.ClientJobDomainService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientJobDomainServiceImpl implements ClientJobDomainService {
    @Autowired
    private ClientJobDomainRepository clientJobDomainRepository; ;

    @Autowired
    private MasterDomainRepository masterDomainRepository;

    @Autowired
    private ClientJobRepository clientJobRepository; ;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ClientJobDomainDto createClientJobDomain(ClientJobDomainDto clientJobDomainDto) {
        validateJobIdAndDomainId(clientJobDomainDto);

        Long maxId = clientJobDomainRepository.findMaxId();
        Long newId = maxId + 1;
        clientJobDomainDto.setClientJobDomainId(newId);


        Long jobId = clientJobDomainDto.getClientJob().getJobId();
        Long domainId = clientJobDomainDto.getDomain().getDomainId();

        // Check if a combination of contactId and domainId already exists

        Optional<ContactDomains>  existingClientJobDomain = clientJobDomainRepository.findByClientJobJobIdAndDomainDomainId(jobId, domainId);

        if (existingClientJobDomain.isPresent()) {
            throw new IllegalArgumentException("The combination of Job ID and Domain ID already exists.");
        }

        ClientJobDomain clientJobDomain = modelMapper.map(clientJobDomainDto, ClientJobDomain.class);
        ClientJobDomain savedClientJobDomain =  clientJobDomainRepository.save(clientJobDomain);
        return modelMapper.map(savedClientJobDomain, ClientJobDomainDto.class);

    }

    @Override
    public ClientJobDomainDto updateClientJobDomain(Long id, ClientJobDomainDto clientJobDomainDto) {
        ClientJobDomain contactDomain =  clientJobDomainRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client Job Domain not found with id: " + id));

//        validateContactIdAndDomainId(contactDomainsDto);
        Long jobId = clientJobDomainDto.getClientJob().getJobId();
        Long domainId = clientJobDomainDto.getDomain().getDomainId();

        // Check if a combination of contactId and domainId already exists
        Optional<ContactDomains> existingClientJobDomain = clientJobDomainRepository.findByClientJobJobIdAndDomainDomainId(jobId, domainId);

        if (existingClientJobDomain.isPresent()) {
            throw new IllegalArgumentException("The combination of Job ID and domain ID already exists.");
        }


//        if (clientJobDomainDto.getIsPrimaryDomain() != null) {
//            contactDomain.setIsPrimaryDomain(clientJobDomainDto.getIsPrimaryDomain());
//        }
//        if (clientJobDomainDto.getIsSecondaryDomain() != null) {
//            contactDomain.setIsSecondaryDomain(clientJobDomainDto.getIsSecondaryDomain());
//        }

//        contactDomain.setIsPrimaryDomain(contactDomainsDto.getIsPrimaryDomain());
//        contactDomain.setIsSecondaryDomain(contactDomainsDto.getIsSecondaryDomain());


        if (clientJobDomainDto.getDomain() != null && clientJobDomainDto.getDomain().getDomainId() != null) {
            MasterDomain masterDomain = masterDomainRepository.findById(clientJobDomainDto.getDomain().getDomainId())
                    .orElseThrow(() -> new ResourceNotFoundException("Domain not found with id: " + clientJobDomainDto.getDomain().getDomainId()));
            contactDomain.setDomain(masterDomain);
        }

        if (clientJobDomainDto.getClientJob() != null && clientJobDomainDto.getClientJob().getJobId() != null) {
            ClientJob clientJob = clientJobRepository.findById(clientJobDomainDto.getClientJob().getJobId())
                    .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + clientJobDomainDto.getClientJob().getJobId()));
            contactDomain.setClientJob(clientJob);
        }

        ClientJobDomain updatedContactDomain = clientJobDomainRepository.save(contactDomain);
        return modelMapper.map(updatedContactDomain, ClientJobDomainDto.class);
    }

    @Override
    public void deleteClientJobDomain(Long id) {
        ClientJobDomain clientJobDomain =  clientJobDomainRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client Job Domain not found with id: " + id));
        clientJobDomainRepository.delete(clientJobDomain);
    }

    @Override
    public ClientJobDomainDto getClientJobDomainById(Long id) {
        ClientJobDomain clientJobDomain =  clientJobDomainRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client Job domain not found with id: " + id));
        return modelMapper.map(clientJobDomain, ClientJobDomainDto.class);
    }

    @Override
    public List<ClientJobDomainDto> getAllClientJobDomains() {
        return clientJobDomainRepository.findAll().stream()
                .map(domains -> modelMapper.map(domains, ClientJobDomainDto.class))
                .collect(Collectors.toList());
    }


    private void validateJobIdAndDomainId( ClientJobDomainDto  clientJobDomainDto) {
        ClientJobDto clientJob = clientJobDomainDto.getClientJob();
        MasterDomainDto domain = clientJobDomainDto.getDomain();

        if (clientJob == null || clientJob.getJobId() == null) {
            throw new IllegalArgumentException("Job ID must be present in the Contact!");
        }

        if (domain == null || domain.getDomainId() == null) {
            throw new IllegalArgumentException("Domain ID must be present in the Domain!");
        }

        if (clientJobDomainDto.getDomain().getDomainId() != null) {
            masterDomainRepository.findById(clientJobDomainDto.getDomain().getDomainId())
                    .orElseThrow(() -> new ResourceNotFoundException("Domain not found with id: " + clientJobDomainDto.getDomain().getDomainId()));

        }

        if (clientJobDomainDto.getClientJob() != null && clientJobDomainDto.getClientJob().getJobId() != null) {
             clientJobRepository.findById(clientJobDomainDto.getClientJob().getJobId())
                    .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " +  clientJobDomainDto.getClientJob().getJobId()));

        }

    }


}
