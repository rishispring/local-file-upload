package com.devwithus.files.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface LocalFileUploadServiceInterface {
    void init();
    String saveFile(MultipartFile file);
    Resource loadFile(String fileName);
}
