package com.hrms.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hrms.entity.Document;
import com.hrms.repository.DocumentRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository repo;
    private final String uploadDir = "C://hrms_uploads";

    public DocumentService(DocumentRepository repo) {
        this.repo = repo;
    }

    public List<Document> listDocuments(Long userId, String category, String search) {
        return repo.findDocuments(userId, category, search);
    }

    public Document uploadDocument(Long userId, MultipartFile file, String category) throws IOException {

        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();

        String uniqueName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String filePath = uploadDir + File.separator + uniqueName;

        File dest = new File(filePath);
        file.transferTo(dest);

        Document doc = new Document();
        doc.setUserId(userId);
        doc.setName(file.getOriginalFilename());
        doc.setType(file.getContentType());
        doc.setSize(file.getSize());
        doc.setCategory(category);
        doc.setUrl(filePath);

        return repo.save(doc);
    }

    public File getFile(Long id) {
        return repo.findById(id).map(d -> new File(d.getUrl())).orElse(null);
    }

    public long getUsedStorage(Long userId) {
        return repo.sumSizeByUserId(userId);
    }

    public boolean deleteDocument(Long id) {
        return repo.findById(id).map(doc -> {
            File f = new File(doc.getUrl());
            if (f.exists()) f.delete();
            repo.delete(doc);
            return true;
        }).orElse(false);
    }

    public void deleteMultiple(List<Long> ids) {
        ids.forEach(this::deleteDocument);
    }

    public String generateShareLink(Long id) {
        Document doc = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        String token = java.util.UUID.randomUUID().toString();
        doc.setShareToken(token);
        doc.setShared(true);
        repo.save(doc);

        return "http://localhost:8081/api/documents/shared/" + token;
    }

    public File getFileByShareToken(String token) {
        Document doc = repo.findByShareToken(token);
        if (doc == null) return null;

        return new File(doc.getUrl());
    }
}
