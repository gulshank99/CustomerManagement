package com.sts.first.CustomerManagement.services;

import com.sts.first.CustomerManagement.exceptions.FileNotFoundCustomException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    public String uploadFile(MultipartFile file, String path) throws IOException;
    public InputStream getResource(String path, String name) throws FileNotFoundException, FileNotFoundCustomException;

}