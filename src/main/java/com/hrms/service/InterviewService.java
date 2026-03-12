package com.hrms.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.dto.InterviewDTO;
import com.hrms.entity.Candidate;
import com.hrms.entity.Interview;
import com.hrms.repository.CandidateRepository;
import com.hrms.repository.InterviewRepository;

@Service
public class InterviewService implements InterviewServiceImpl {

    @Autowired
    private InterviewRepository interviewRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public Interview createInterview(Long candidateId, Interview interview) {

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() ->
                        new RuntimeException("Candidate not found with id: " + candidateId));

        interview.setCandidate(candidate);

        if (interview.getPanel() == null) {
            interview.setPanel(new java.util.ArrayList<>());
        }

        return interviewRepository.save(interview);
    }

    @Override
    public List<InterviewDTO> getAllInterviews() {
        return interviewRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InterviewDTO getInterviewById(Long id) {

        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Interview not found with id: " + id));

        return mapToDTO(interview);
    }

    @Override
    public Interview updateInterview(Long id, Interview interview) {

        Interview existing = interviewRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Interview not found with id: " + id));

        existing.setInterviewType(interview.getInterviewType());
        existing.setInterviewDate(interview.getInterviewDate());
        existing.setStartTime(interview.getStartTime());
        existing.setEndTime(interview.getEndTime());
        existing.setPlatform(interview.getPlatform());
        existing.setStatus(interview.getStatus());

        if (interview.getPanel() != null) {
            existing.setPanel(interview.getPanel());
        } else {
            existing.setPanel(new java.util.ArrayList<>());
        }

        return interviewRepository.save(existing);
    }

    @Override
    public void deleteInterview(Long id) {
        interviewRepository.deleteById(id);
    }

    private InterviewDTO mapToDTO(Interview interview) {

        InterviewDTO dto = new InterviewDTO();

        dto.setId(interview.getId());

        dto.setCandidate(interview.getCandidate().getName());
        dto.setRole(interview.getCandidate().getJob().getTitle());

        dto.setType(interview.getInterviewType());

        DateTimeFormatter timeFmt = DateTimeFormatter.ofPattern("hh:mm a");
        dto.setTime(
                interview.getStartTime().format(timeFmt)
                        + " - " +
                interview.getEndTime().format(timeFmt)
        );

        DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("MMM dd");
        dto.setDate(
                interview.getInterviewDate().format(dateFmt)
        );

        dto.setPanel(interview.getPanel());
        dto.setStatus(interview.getStatus());
        dto.setPlatform(interview.getPlatform());

        return dto;
    }
}