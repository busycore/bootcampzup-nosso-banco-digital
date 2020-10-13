package com.zupchallenge.SpringBootChallenge.shared.providers.FileUploadProvider;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadProvider {

    String uploadFile(MultipartFile file);
}
