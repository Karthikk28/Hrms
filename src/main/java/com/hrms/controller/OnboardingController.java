package com.hrms.controller;

import com.hrms.dto.OnboardingCandidateDTO;
import com.hrms.dto.OnboardingStepDTO;
import com.hrms.dto.OnboardingTaskStatusDTO;
import com.hrms.entity.Candidate;
import com.hrms.entity.CandidateStatus;
import com.hrms.entity.OnboardingTaskStatus;
import com.hrms.repository.CandidateRepository;
import com.hrms.repository.OnboardingTaskStatusRepository;
import com.hrms.service.EmailService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/onboarding")
@CrossOrigin(origins = "http://localhost:3000")
public class OnboardingController {

    private final OnboardingTaskStatusRepository repo;
    private final CandidateRepository candidateRepo;

    private final EmailService emailService;

    public OnboardingController(OnboardingTaskStatusRepository repo,
                                CandidateRepository candidateRepo,
                                EmailService emailService) {
        this.repo = repo;
        this.candidateRepo = candidateRepo;
        this.emailService = emailService;
    }

    @PostMapping("/task")
    public void saveTask(@RequestBody OnboardingTaskStatusDTO dto) {

        OnboardingTaskStatus status =
                repo.findByCandidateIdAndStepNameAndTaskName(
                        dto.getCandidateId(),
                        dto.getStepName(),
                        dto.getTaskName()
                ).orElse(new OnboardingTaskStatus());

        Candidate candidate = candidateRepo.findById(dto.getCandidateId())
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        status.setCandidate(candidate);
        status.setStepName(dto.getStepName());
        status.setTaskName(dto.getTaskName());
        status.setCompleted(dto.isCompleted());

        repo.save(status);
    }

    @GetMapping("/all-offered")
    public List<OnboardingCandidateDTO> getAllOfferedCandidatesWithSteps() {
      
        List<Candidate> offeredCandidates = candidateRepo.findByCurrentStatus(CandidateStatus.OFFERED);

        return offeredCandidates.stream().map(candidate -> {
    
            List<OnboardingTaskStatus> savedStatuses = repo.findByCandidateId(candidate.getId());

            var savedMap = savedStatuses.stream()
                    .collect(Collectors.toMap(
                            s -> s.getStepName() + "-" + s.getTaskName(),
                            OnboardingTaskStatus::isCompleted
                    ));

            List<OnboardingStepDTO> steps = List.of(
            		
            		   new OnboardingStepDTO(1L, "Offer Signed",
                               List.of("Review Offer", "Sign Digital Contract", "NDA Acceptance"),
                               List.of(
                                       savedMap.getOrDefault("Offer Signed-Review Offer", false),
                                       savedMap.getOrDefault("Offer Signed-Sign Digital Contract", false),
                                       savedMap.getOrDefault("Offer Signed-NDA Acceptance", false)
                               )
                       ),
                    new OnboardingStepDTO(2L, "Background Check",
                            List.of("Identity Verification", "Criminal Record Check", "Employment History"),
                            List.of(
                                    savedMap.getOrDefault("Background Check-Identity Verification", false),
                                    savedMap.getOrDefault("Background Check-Criminal Record Check", false),
                                    savedMap.getOrDefault("Background Check-Employment History", false)
                            )
                    ),
                    new OnboardingStepDTO(3L, "IT Provisioning",
                            List.of("Laptop Configuration", "Email Creation", "GitHub Access", "Slack Invite"),
                            List.of(
                                    savedMap.getOrDefault("IT Provisioning-Laptop Configuration", false),
                                    savedMap.getOrDefault("IT Provisioning-Email Creation", false),
                                    savedMap.getOrDefault("IT Provisioning-GitHub Access", false),
                                    savedMap.getOrDefault("IT Provisioning-Slack Invite", false)

                            )
                    ),
                    new OnboardingStepDTO(4L, "Document Upload",
                            List.of("Passport Scan", "Tax Documents", "Bank Details"),
                            List.of(
                                    savedMap.getOrDefault("Document Upload-Passport Scan", false),
                                    savedMap.getOrDefault("Document Upload-Tax Documents", false),
                                    savedMap.getOrDefault("Document Upload-Bank Details", false)
                            )
                    )
                 
            );

            String avatar = candidate.getName() != null ?
                    candidate.getName().chars()
                            .filter(Character::isLetter)
                            .mapToObj(c -> String.valueOf((char) c))
                            .limit(2)
                            .collect(Collectors.joining())
                            .toUpperCase()
                    : "";

            return new OnboardingCandidateDTO(
                    candidate.getId(),
                    candidate.getName(),
                    candidate.getJob() != null ? candidate.getJob().getTitle() : null,
                    avatar,
                    steps
            );
        }).toList();
    }

	public EmailService getEmailService() {
		return emailService;
	}
	@PostMapping("/resend-welcome/{candidateId}")
	public void resendWelcomeEmail(@PathVariable Long candidateId) {

	    Candidate candidate = candidateRepo.findById(candidateId)
	            .orElseThrow(() -> new RuntimeException("Candidate not found"));

	    String subject = "Welcome to ZentraHR 🎉";

	    String message = """
	            Hi %s,

	            Welcome to ZentraHR!

	            Your onboarding process has started.
	            Please log in to the portal and complete your onboarding process.

	            If you have any questions, feel free to contact HR.

	            Regards,
	            ZentraHR Team
	            """.formatted(candidate.getName());

	    emailService.sendEmail(candidate.getEmail(), subject, message);
	}
}