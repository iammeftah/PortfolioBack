package com.example.portfolioback.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ResumeDownloaderController {

    @GetMapping("/download-resume")
    public ResponseEntity<InputStreamResource> downloadResume() {
        try {
            // Load the file from the classpath
            ClassPathResource resumeFile = new ClassPathResource("assets/MEFTAH AhmedReda Resume.pdf");
            InputStreamResource resource = new InputStreamResource(resumeFile.getInputStream());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=resume.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(resumeFile.contentLength())
                    .body(resource);
        } catch (IOException e) {
            System.out.println("Error downloading file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
