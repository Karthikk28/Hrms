package com.hrms.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hrms.entity.Message;
import com.hrms.repository.MessageRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageRepository repo;

    @PostMapping
    public Message sendMessage(@RequestBody Message message) {
        message.setTimestamp(LocalDateTime.now());
        return repo.save(message);
    }
    @GetMapping
    public List<Message> getMessages(
            @RequestParam Long senderId,
            @RequestParam Long receiverId
    ) {
    	return repo.getConversation(senderId, receiverId);
    }
    @DeleteMapping("/clear")
    @Transactional
    public void clearChat(
            @RequestParam Long senderId,
            @RequestParam Long receiverId
    ) {
        repo.deleteConversation(senderId, receiverId);
    }

    
}
