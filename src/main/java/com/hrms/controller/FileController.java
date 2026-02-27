package com.hrms.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            if (!java.nio.file.Files.exists(filePath)) {
                return ResponseEntity.notFound().build();
            }
            java.net.URI fileUri = filePath.toUri();
            if (fileUri == null) {
                return ResponseEntity.internalServerError().build();
            }
            Resource resource = new UrlResource(fileUri);

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=\"" + resource.getFilename() + "\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);

        } catch (java.nio.file.InvalidPathException | java.net.MalformedURLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
