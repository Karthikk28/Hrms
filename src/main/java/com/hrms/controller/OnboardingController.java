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
            
Welcome to ZentraHR! Your Onboarding Process



Welcome to the family! We're thrilled to have you join our team. As you
embark on this exciting journey with us, we want to ensure that your
onboarding experience is smooth and comprehensive.

Below, you'll find an outline of the onboarding process

Orientation Schedule: Your first day will begin with an orientation
session where you'll get acquainted with our company culture, values,
and mission. You'll also receive an overview of your role and
responsibilities.

HR Paperwork: Please ensure you have completed all necessary paperwork
provided by our HR department. This includes tax forms, employee
agreements, and any other relevant documents.

Technology Setup: Our IT team will assist you in setting up your
workstation, email account, and any other tools necessary for your role.

Training Sessions: Throughout your first week, you'll participate in
various training sessions to familiarize yourself with our systems,
processes, and tools. This will help you hit the ground running in your
new role.

Meetings with Team Members: You'll have the opportunity to meet with
your immediate team members and key stakeholders to introduce yourself,
learn about their roles, and understand how your work aligns with
theirs.

Buddy/ Mentor Program: As part of our onboarding process, you'll be
paired with a buddy or mentor who will guide you through your initial
weeks at ZentraHR. They'll be your go-to person
for any questions or concerns you may have.

Feedback and Check-ins: We value your feedback and want to ensure you
have the support you need to succeed. Regular check-ins will be
scheduled with your manager to discuss your progress, address any
challenges, and set goals for your development.

Company Policies and Benefits: Familiarize yourself with our company
policies, including our code of conduct, safety procedures, and employee
benefits. Our HR team will provide you with detailed information and
answer any questions you may have.

If you have any questions or need further assistance at any point during
your onboarding process, please don't hesitate to reach out to HR Team.

Once again, welcome to ZentraHR! We're
thrilled to have you on board and look forward to working together.

Best regards,
ZentraHR Team
	            """.formatted(candidate.getName());

	    emailService.sendEmail(candidate.getEmail(), subject, message);
	}
}