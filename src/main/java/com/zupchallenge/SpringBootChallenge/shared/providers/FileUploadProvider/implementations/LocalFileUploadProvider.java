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

public class LocalFileUploadProvider implements IFileUploadProvider {

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    @Override
    public String uploadFile(MultipartFile file) {

        try {
            Path copyLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            return copyLocation.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
    }

}
