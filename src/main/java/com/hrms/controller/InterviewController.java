package com.hrms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hrms.dto.InterviewDTO;
import com.hrms.entity.Interview;
import com.hrms.service.InterviewServiceImpl;

@RestController
@RequestMapping("/api/interviews")
@CrossOrigin(origins = "http://localhost:3000")
public class InterviewController {

    private final InterviewServiceImpl interviewService;

    public InterviewController(InterviewServiceImpl interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping("/candidate/{candidateId}")
    public ResponseEntity<Interview> createInterview(
            @PathVariable Long candidateId,
            @RequestBody Interview interview) {

        return ResponseEntity.ok(
                interviewService.createInterview(candidateId, interview));
    }

    @GetMapping
    public ResponseEntity<List<InterviewDTO>> getAllInterviews() {
        return ResponseEntity.ok(interviewService.getAllInterviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterviewDTO> getInterviewById(
            @PathVariable Long id) {
        return ResponseEntity.ok(interviewService.getInterviewById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Interview> updateInterview(
            @PathVariable Long id,
            @RequestBody Interview interview) {

        return ResponseEntity.ok(
                interviewService.updateInterview(id, interview));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInterview(
            @PathVariable Long id) {

        interviewService.deleteInterview(id);
        return ResponseEntity.ok("Interview deleted successfully");
    }
}