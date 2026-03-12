package com.hrms.dto;

import java.util.List;

public record OnboardingStepDTO(
        Long id,
        String name,
        List<String> tasks,
        List<Boolean> taskChecks
) {}