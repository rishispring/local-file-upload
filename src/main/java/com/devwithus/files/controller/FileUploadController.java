package com.devwithus.files.controller;

import com.devwithus.files.service.LocalFileUploadServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    LocalFileUploadServiceInterface localFileUploadService;

    @PostMapping("/uploadfile")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("file")MultipartFile file){
        String upfile = localFileUploadService.saveFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/download")
                .path(upfile)
                .toUriString();
        return ResponseEntity.status(HttpStatus.OK).
                body(new FileResponse(
                        upfile,
                        fileDownloadUri,
                        "File upload success"));
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName){
        Resource resource =  localFileUploadService.loadFile(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; fileName=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
