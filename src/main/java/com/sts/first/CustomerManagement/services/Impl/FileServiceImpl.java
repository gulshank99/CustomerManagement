package com.sts.first.CustomerManagement.services.Impl;

import com.sts.first.CustomerManagement.exceptions.BadApiRequestException;
import com.sts.first.CustomerManagement.exceptions.FileNotFoundCustomException;
import com.sts.first.CustomerManagement.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    // Directory structure under Resources
    private static final String BASE_DIR = "Assets";
    private static final String IMAGE_DIR = BASE_DIR + "/Images";
    private static final String RESUME_DIR = BASE_DIR + "/Resumes";
    private static final String JOB_DESCRIPTION_DIR = BASE_DIR + "/JobDescriptions";

    @Override
    public String uploadFile(MultipartFile file, String type) throws IOException {
        String originalFilename = file.getOriginalFilename();
        logger.info("File Name : {}", originalFilename);

        // Generate unique filename
        String filename = UUID.randomUUID().toString();
        String extension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        String fileNameWithExtension = filename + extension;
        String targetDirectory;

        // Determine the target directory based on file type
        switch (type.toLowerCase()) {
            case "image":
                if (!isAllowedImageExtension(extension)) {
                    throw new BadApiRequestException("Invalid image format: " + extension);
                }
                targetDirectory = IMAGE_DIR;
                break;
            case "resume":
                if (!isAllowedDocExtension(extension)) {
                    throw new BadApiRequestException("Invalid document format for resume: " + extension);
                }
                targetDirectory = RESUME_DIR;
                break;
            case "job_description":
                if (!isAllowedDocExtension(extension)) {
                    throw new BadApiRequestException("Invalid document format for job description: " + extension);
                }
                targetDirectory = JOB_DESCRIPTION_DIR;
                break;
            default:
                throw new BadApiRequestException("Invalid file type specified");
        }

        // Ensure the folder exists
        File folder = new File(targetDirectory);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fullPathWithFileName = targetDirectory + File.separator + fileNameWithExtension;
        // Upload the file
        Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));

        return fileNameWithExtension;
    }

  @Override
  public InputStream getResource(String type, String name)
      throws FileNotFoundException, FileNotFoundCustomException {

        String targetDirectory;
        switch (type.toLowerCase()) {
            case "image":
                targetDirectory = IMAGE_DIR;
                break;
            case "resume":
                targetDirectory = RESUME_DIR;
                break;
            case "jd":
                targetDirectory = JOB_DESCRIPTION_DIR;
                break;
            default:
                throw new BadApiRequestException("Invalid file type specified");
        }
      String fullPath = targetDirectory + File.separator + name;
      File file = new File(fullPath);

      if (!file.exists()) {
          logger.error("File not found: {}", fullPath);
          throw new FileNotFoundCustomException("File not found at path: " + fullPath);
      }

      try {
          return new FileInputStream(file);
      } catch (FileNotFoundException e) {
          logger.error("Error retrieving file: {}", e.getMessage());
          throw new FileNotFoundCustomException("Error retrieving file: " + e.getMessage());
      }
    }

    private boolean isAllowedImageExtension(String extension) {
        return extension.equals(".png") || extension.equals(".jpeg") || extension.equals(".jpg");
    }

    private boolean isAllowedDocExtension(String extension) {
        return extension.equals(".pdf") || extension.equals(".docx") || extension.equals(".doc");
    }
}

