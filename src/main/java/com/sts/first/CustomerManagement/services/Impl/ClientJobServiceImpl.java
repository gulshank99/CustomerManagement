package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.dtos.ClientJobDto;
import com.sts.first.CustomerManagement.dtos.ClientLocationDto;
import com.sts.first.CustomerManagement.dtos.MasterClientDto;
import com.sts.first.CustomerManagement.dtos.MasterLocationDto;
import com.sts.first.CustomerManagement.entities.ClientJob;
import com.sts.first.CustomerManagement.entities.ContactDetails;
import com.sts.first.CustomerManagement.entities.MasterClient;
import com.sts.first.CustomerManagement.exceptions.FileNotFoundCustomException;
import com.sts.first.CustomerManagement.exceptions.ResourceNotFoundException;
import com.sts.first.CustomerManagement.repositories.ClientJobRepository;
import com.sts.first.CustomerManagement.repositories.MasterClientRepository;
import com.sts.first.CustomerManagement.services.ClientJobService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
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

        ClientJob job = modelMapper.map(jobDto, ClientJob.class);
        ClientJob savedJob = clientJobRepository.save(job);
        return modelMapper.map(savedJob, ClientJobDto.class);
    }

    @Override
    public ClientJobDto updateJob(Long jobId, ClientJobDto jobDto) {
        ClientJob job = clientJobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + jobId));

        if (jobDto.getJobCode() != null) {
            job.setJobCode(jobDto.getJobCode());
        }
//        if (jobDto.getJd() != null) {
//            job.setJd(jobDto.getJd());
//        }
        if (jobDto.getJobTitle() != null) {
            job.setJobTitle(jobDto.getJobTitle());
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
}


