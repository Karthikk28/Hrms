package com.hrms.service;

import java.util.List;
import com.hrms.dto.InterviewDTO;
import com.hrms.entity.Interview;

public interface InterviewServiceImpl {

    Interview createInterview(Long candidateId, Interview interview);

    List<InterviewDTO> getAllInterviews();

    InterviewDTO getInterviewById(Long id);

    Interview updateInterview(Long id, Interview interview);

    void deleteInterview(Long id);
}