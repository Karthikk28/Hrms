package com.hrms.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.hrms.entity.Document;
import com.hrms.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    private final DocumentService service;
    public DocumentController(DocumentService service) {
        this.service = service;
    }
    @GetMapping("/list")
    public List<Document> listDocuments(
            @RequestParam Long userId,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String search
    ) {
        return service.listDocuments(userId, category, search);
    }
    @GetMapping("/storage")
    public Map<String, Object> getStorage(@RequestParam Long userId) {
        long used = service.getUsedStorage(userId);
        long total = 50L * 1024 * 1024 * 1024;

        return Map.of(
            "used", used,
            "total", total
        );
    }
    @PostMapping("/upload")
    public Document upload(@RequestParam Long userId, @RequestParam MultipartFile file, @RequestParam String category) throws IOException {
        return service.uploadDocument(userId, file, category);
    }
    @GetMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam Long id) throws IOException {

        File file = service.getFile(id);
        if (file == null || !file.exists()) {
            return ResponseEntity.notFound().build();
        }

        byte[] data = java.nio.file.Files.readAllBytes(file.toPath());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
                .header(HttpHeaders.CONTENT_TYPE, java.nio.file.Files.probeContentType(file.toPath()))
                .body(data);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long id) {
        boolean deleted = service.deleteDocument(id);
        if (deleted) {
            return ResponseEntity.ok("Document deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Document not found");
        }
    }
    @DeleteMapping("/delete-multiple")
    public ResponseEntity<?> deleteMultiple(@RequestBody List<Long> ids) {
        service.deleteMultiple(ids);
        return ResponseEntity.ok("Documents deleted successfully");
    }
    @PostMapping("/share")
    public ResponseEntity<Map<String, String>> shareDocument(@RequestBody Map<String, Object> body) {
        Integer id = (Integer) body.get("id");

        String shareUrl = "http://localhost:3000/share/view?id=" + id;

        Map<String, String> response = new HashMap<>();
        response.put("shareUrl", shareUrl);

        return ResponseEntity.ok(response);
    }      
    @GetMapping("/shared/{token}")
    public ResponseEntity<byte[]> downloadShared(@PathVariable String token) throws IOException {
        File file = service.getFileByShareToken(token);

        if (file == null || !file.exists()) {
            return ResponseEntity.notFound().build();
        }

        byte[] data = java.nio.file.Files.readAllBytes(file.toPath());

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
            .header(HttpHeaders.CONTENT_TYPE, java.nio.file.Files.probeContentType(file.toPath()))
            .body(data);
    }

}
