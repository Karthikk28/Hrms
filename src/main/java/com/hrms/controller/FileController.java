package com.hrms.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private static final String BASE_PATH = "C:/hrms-data";
    @GetMapping("/profile/{employeeId}")
    public ResponseEntity<Resource> getProfileImage(@PathVariable Long employeeId) {
        return serveFile("profile", employeeId, "profile.jpg");
    }
    @GetMapping("/documents/{employeeId}/{fileName}")
    public ResponseEntity<Resource> getDocument(
            @PathVariable Long employeeId,
            @PathVariable String fileName) {
        return serveFile("documents", employeeId, fileName);
    }
    @GetMapping("/letters/{employeeId}/{fileName}")
    public ResponseEntity<Resource> getLetter(
            @PathVariable Long employeeId,
            @PathVariable String fileName) {
        return serveFile("letters", employeeId, fileName);
    }
    private ResponseEntity<Resource> serveFile(String type, Long employeeId, String fileName) {
        try {
            Path filePath = Paths.get(BASE_PATH, type, employeeId.toString(), fileName);
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=\"" + resource.getFilename() + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
