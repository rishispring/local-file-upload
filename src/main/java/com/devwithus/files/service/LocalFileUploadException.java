package com.devwithus.files.service;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class LocalFileUploadException extends RuntimeException{
    private String message;
}
