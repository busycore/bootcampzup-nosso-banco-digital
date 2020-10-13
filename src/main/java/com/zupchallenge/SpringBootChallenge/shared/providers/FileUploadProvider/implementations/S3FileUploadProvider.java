package com.zupchallenge.SpringBootChallenge.shared.providers.FileUploadProvider.implementations;

import com.zupchallenge.SpringBootChallenge.shared.errors.exceptions.FileStorageException;
import com.zupchallenge.SpringBootChallenge.shared.providers.FileUploadProvider.IFileUploadProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

//This implementation has no real code, it's here only to demonstrate the flexibility of Dependency Injection
public class S3FileUploadProvider implements IFileUploadProvider {

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    @Override
    public String uploadFile(MultipartFile file) {
        //TODO Implements  Amazon's S3 CDN File Upload service
        //
        return "";
    }
}
