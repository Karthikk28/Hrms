package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.hrms.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("""
        SELECT m FROM Message m
        WHERE 
        (m.senderId = :senderId AND m.receiverId = :receiverId)
        OR
        (m.senderId = :receiverId AND m.receiverId = :senderId)
        ORDER BY m.timestamp ASC
    """)
    List<Message> getConversation(
        @Param("senderId") Long senderId,
        @Param("receiverId") Long receiverId
    );
    @Modifying
    @Query("DELETE FROM Message m WHERE (m.senderId = :senderId AND m.receiverId = :receiverId) OR (m.senderId = :receiverId AND m.receiverId = :senderId)")
    void deleteConversation(Long senderId, Long receiverId);
   
}