package com.hrms.repository;

import com.hrms.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findByUserId(Long userId);

    List<Document> findByUserIdAndCategory(Long userId, String category);

    List<Document> findByUserIdAndNameContainingIgnoreCase(Long userId, String search);

    @Query("""
        SELECT d FROM Document d
        WHERE d.userId = :userId
        AND (:category IS NULL OR d.category = :category)
        AND (:search IS NULL OR LOWER(d.name) LIKE LOWER(CONCAT('%', :search, '%')))
    """)
    List<Document> findDocuments(Long userId, String category, String search);

    @Query("SELECT COALESCE(SUM(d.size), 0) FROM Document d WHERE d.userId = :userId")
    long sumSizeByUserId(Long userId);

    Document findByShareToken(String token);
}
