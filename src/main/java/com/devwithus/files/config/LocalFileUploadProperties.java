package com.devwithus.files.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "file.upload")
public class LocalFileUploadProperties {
    private String location;
    public String getLocation(){
        return this.location;
    }

    public void setLocation(String location){
        this.location = location;
    }

}
