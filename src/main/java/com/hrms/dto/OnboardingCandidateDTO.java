package com.hrms.dto;

import java.util.List;

public record OnboardingCandidateDTO(
        Long id,
        String name,
        String role,
        String avatar,
        List<OnboardingStepDTO> steps
) {}