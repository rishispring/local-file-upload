package com.devwithus.files;

import com.devwithus.files.config.LocalFileUploadProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		LocalFileUploadProperties.class
})
public class LocalFileUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalFileUploadApplication.class, args);
	}

}
