// If connected to amazon aws s3
// https://docs.aws.amazon.com/AmazonS3/latest/userguide/example_s3_PutObject_section.html

package com.devwithus.files.service;

import com.devwithus.files.config.LocalFileUploadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import com.devwithus.files.service.FileNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class LocalFileUploadService implements LocalFileUploadServiceInterface{
    private final Path dirLocation;
    @Autowired
    public LocalFileUploadService(LocalFileUploadProperties localFileUploadProperties){
        this.dirLocation = Paths.get(localFileUploadProperties.getLocation())
                            .toAbsolutePath()
                            .normalize();

    }

    @Override
    @PostConstruct
    public void init(){
        try{
            Files.createDirectories(this.dirLocation);
        }
        catch (Exception ex){
            throw new LocalFileUploadException("Could not upload file");
        }
    }

    @Override
    public String saveFile(MultipartFile file) {

        try {
            String fileName = file.getOriginalFilename();
            Path dfile = this.dirLocation.resolve(fileName);
            Files.copy(file.getInputStream(), dfile, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (Exception e) {
            throw new LocalFileUploadException("Could not upload file");
        }
    }


    // Given fileName, returns us the resource or throws exception
    @Override
    public Resource loadFile(String fileName){
        try{
            Path file = this.dirLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()){
                return resource;
            }
            else{
                throw new FileNotFoundException("Could not find file");
            }
        }
        catch (MalformedURLException e){
            throw new FileNotFoundException("Could not download file");
        }
    }

}
