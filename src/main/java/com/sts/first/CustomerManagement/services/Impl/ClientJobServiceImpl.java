package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.dtos.ClientJobDto;
import com.sts.first.CustomerManagement.dtos.ClientLocationDto;
import com.sts.first.CustomerManagement.dtos.MasterClientDto;
import com.sts.first.CustomerManagement.dtos.MasterLocationDto;
import com.sts.first.CustomerManagement.entities.ClientJob;
import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.ContactInterviews;
import com.sts.first.CustomerManagement.entities.MasterClient;
import com.sts.first.CustomerManagement.exceptions.DuplicateResourceException;
import com.sts.first.CustomerManagement.exceptions.FileNotFoundCustomException;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.ClientJobRepository;
import com.sts.first.CustomerManagement.repositories.MasterClientRepository;
import com.sts.first.CustomerManagement.services.ClientJobService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

import static com.sts.first.CustomerManagement.services.Impl.FileServiceImpl.logger;

@Service
public class ClientJobServiceImpl implements ClientJobService {

    @Autowired
    private ClientJobRepository clientJobRepository;
    @Autowired
    MasterClientRepository masterClientRepository;
    private final String JdPath = "Assets/JobDescription/";

    Logger logger = LoggerFactory.getLogger(ClientJobServiceImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ClientJobDto createJob(ClientJobDto jobDto) {
        validateClientId(jobDto);
        Long newId = clientJobRepository.findMaxId() + 1;
        jobDto.setJobId(newId);

        if (jobDto.getJobCode() == null || jobDto.getJobCode().isEmpty()) {
            String generatedJobCode = generateJobCode(jobDto);
            jobDto.setJobCode(generatedJobCode);
        }

        if(clientJobRepository.findByJobCode(jobDto.getJobCode()).isPresent()) {
            throw new DuplicateResourceException("Job Code already exists. Please use a different Job Code.");
        }

//        Optional<ClientJob> existsByJobTitleAndClientId = clientJobRepository.findByJobTitleAndClient_ClientId(jobDto.getJobTitle(), jobDto.getClient().getClientId());
//
//        if (existsByJobTitleAndClientId.isPresent()) {
//            throw new IllegalArgumentException("The combination of JobTitle and Client ID already exists.");
//        }

        ClientJob job = modelMapper.map(jobDto, ClientJob.class);
        ClientJob savedJob = clientJobRepository.save(job);
        return modelMapper.map(savedJob, ClientJobDto.class);
    }

    @Override
    public ClientJobDto updateJob(Long jobId, ClientJobDto jobDto) {
        ClientJob job = clientJobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + jobId));

//        Optional<ClientJob> existsByJobTitleAndClientId = clientJobRepository.findByJobTitleAndClient_ClientId(jobDto.getJobTitle(), jobDto.getClient().getClientId());
//
//        if (existsByJobTitleAndClientId.isPresent()) {
//            throw new IllegalArgumentException("The combination of JobTitle and Client ID already exists.");
//        }

        if(clientJobRepository.findByJobCode(jobDto.getJobCode()).isPresent()) {
            throw new DuplicateResourceException("Job Code already exists. Please use a different Job Code.");
        }

        if (jobDto.getJobCode() != null) {
            job.setJobCode(jobDto.getJobCode());
        }
//        if (jobDto.getJd() != null) {
//            job.setJd(jobDto.getJd());
//        }
        if (jobDto.getJobTitle() != null) {
            job.setJobTitle(jobDto.getJobTitle());
        }

        if (jobDto.getPostCreatedOn() != null) {
            job.setPostCreatedOn(jobDto.getPostCreatedOn());
        }

        if (jobDto.getIsJobActive() != null) {
            job.setIsJobActive(jobDto.getIsJobActive());
        }
        if (jobDto.getJobPostType() != null ) {
            job.setJobPostType(jobDto.getJobPostType());
        }

        if (jobDto.getInsertedBy() != null) {
            job.setInsertedBy(jobDto.getInsertedBy());
        }

        if (jobDto.getClient() != null) {
            MasterClient masterClient = masterClientRepository.findById(jobDto.getClient().getClientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + jobDto.getClient().getClientId()));
            job.setClient(masterClient);
        }

        ClientJob updatedJob = clientJobRepository.save(job);
        return modelMapper.map(updatedJob, ClientJobDto.class);
    }

    @Override
    public void deleteJob(Long jobId) {
        ClientJob job = clientJobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + jobId));
        clientJobRepository.delete(job);
    }

    @Override
    public ClientJobDto getJobById(Long jobId) {
        ClientJob job = clientJobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + jobId));
        return modelMapper.map(job, ClientJobDto.class);
    }

    @Override
    public List<ClientJobDto> getAllJobs() {
        return clientJobRepository.findAll().stream()
                .map(job -> modelMapper.map(job, ClientJobDto.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<ClientJobDto> getJobsByClientId(Long clientId) {
        List<ClientJob> jobs = clientJobRepository.findByClient_ClientId(clientId);
        if (jobs.isEmpty()) {
            throw new ResourceNotFoundException("No jobs found for client ID: " + clientId);
        }
        return jobs.stream()
                .map(job -> modelMapper.map(job, ClientJobDto.class))
                .collect(Collectors.toList());
    }





    @Override
    public void updateJdField(Long jobId, String value) {
        ClientJob clientJob = clientJobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("JD not found with id: " + jobId));

        String jobDescription =  clientJob.getJd();

        if (jobDescription != null && !jobDescription.isEmpty()) {
            String fullPath= JdPath+jobDescription;
            File file = Paths.get(fullPath).toFile();

            if (file.exists()) {
                file.delete();
                logger.info("File deleted successfully");
            } else {
                logger.warn("File not found, cannot delete: {}", fullPath);
                // throw new FileNotFoundCustomException("The file to delete does not exist: " + fullPath);
            }
        }

        clientJobRepository.updateJdByJobId(jobId, value);
    }


    private void validateClientId(ClientJobDto clientJobDto)  {

        MasterClientDto client = clientJobDto.getClient();

        if (client == null || client.getClientId() == null) {
            throw new IllegalArgumentException("Client ID must be present in the Client!");
        }


        if (clientJobDto.getClient().getClientId() != null) {
            masterClientRepository.findById(clientJobDto.getClient().getClientId())
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " +  clientJobDto.getClient().getClientId()));
        }

    }

    private String generateJobCode(ClientJobDto jobDto) {
        // Get the first 4 letters of the company name
        Optional<MasterClient> client = masterClientRepository.findById(jobDto.getClient().getClientId());
        String companyName = client.get().getClientName();
        String companyPrefix = companyName.length() >= 4 ? companyName.substring(0, 4) : companyName;

        // Get the technology
        String technology = jobDto.getJobTitle();
        String[] splitStr = technology.split("\\s+");
        technology = splitStr[0];

        // Get the current date in DDMMYYYY format
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String formattedDate = currentDate.format(formatter);

        String formattedJobCode = String.format("%s-%s-%s", companyPrefix, technology, formattedDate);

        String jobPostType = jobDto.getJobPostType().toLowerCase();
        if( jobPostType.equals("new")){
            formattedJobCode =  "N-" + formattedJobCode ;
        }
        else{
            formattedJobCode =  "R-" + formattedJobCode ;
        }

        if (clientJobRepository.findByJobCode(formattedJobCode).isPresent()){
            formattedJobCode =  formattedJobCode + "-"+ UUID.randomUUID().toString().substring(0,5);
        }


        // Construct the JobCode
        return formattedJobCode;
    }
}


