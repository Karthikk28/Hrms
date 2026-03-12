package com.hrms.service;

import com.hrms.dto.ApplyLeaveRequestResponse;
import com.hrms.dto.LeaveRequestResponse;
import com.hrms.entity.Employee;
import com.hrms.entity.LeaveRequest;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.LeaveRequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveRequestServiceImpl(
            LeaveRequestRepository leaveRequestRepository,
            EmployeeRepository employeeRepository
    ) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<LeaveRequestResponse> getLeaveRequests(Long employeeId) {

        List<LeaveRequest> requests =
                leaveRequestRepository.findByEmployeeWithApprover(employeeId);

        List<LeaveRequestResponse> responseList = new ArrayList<>();

        for (LeaveRequest r : requests) {
            responseList.add(mapToResponse(r));
        }

        return responseList;
    }

    @Override
    public LeaveRequestResponse applyLeave(
            Long employeeId,
            ApplyLeaveRequestResponse request,
            MultipartFile file
    ) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        float totalDays = calculateDays(
                request.getFromDate(),
                request.getToDate(),
                request.getHalfDay()
        );

        LeaveRequest leave = new LeaveRequest();
        leave.setEmployee(employee);
        leave.setLeaveType(normalizeType(request.getType()));
        leave.setReason(request.getReason());
        leave.setStartDate(request.getFromDate());
        leave.setEndDate(request.getToDate());
        leave.setTotalDays(totalDays);
        leave.setStatus("Pending");
        leave.setAppliedAt(LocalDateTime.now());

        leaveRequestRepository.save(leave);

        return mapToResponse(leave);
    }

    private float calculateDays(
            LocalDate from,
            LocalDate to,
            Boolean halfDay
    ) {
        if (from == null || to == null) return 0f;

        long days = ChronoUnit.DAYS.between(from, to) + 1;
        return Boolean.TRUE.equals(halfDay)
                ? days - 0.5f
                : days;
    }

    private String normalizeType(String type) {
        if (type == null) return null;

        return switch (type.toLowerCase()) {
            case "sick" -> "Sick Leave";
            case "casual" -> "Casual Leave";
            case "privilege" -> "Privilege Leave";
            case "wfh" -> "WFH";
            default -> type;
        };
    }

    private LeaveRequestResponse mapToResponse(LeaveRequest r) {

        LeaveRequestResponse dto = new LeaveRequestResponse();
        dto.setId(r.getId());
        dto.setType(r.getLeaveType());
        dto.setDays(r.getTotalDays());
        dto.setStatus(r.getStatus());
        dto.setReason(r.getReason());

        dto.setDates(
                r.getStartDate() != null && r.getEndDate() != null
                        ? r.getStartDate() + " to " + r.getEndDate()
                        : "-"
        );

        dto.setPendingWith(
                r.getApprovedBy() != null
                        ? r.getApprovedBy().getFirstName()
                        : "Manager"
        );

        dto.setWorkflow(buildWorkflow(r));
        return dto;
    }

    private List<LeaveRequestResponse.WorkflowStep> buildWorkflow(
            LeaveRequest r
    ) {

        DateTimeFormatter fmt =
                DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

        List<LeaveRequestResponse.WorkflowStep> steps = new ArrayList<>();

        LeaveRequestResponse.WorkflowStep applied =
                new LeaveRequestResponse.WorkflowStep();

        applied.setStep("Leave Applied");
        applied.setBy("Employee");
        applied.setRole("Self");
        applied.setStatus("completed");
        applied.setTime(
                r.getAppliedAt() != null
                        ? r.getAppliedAt().format(fmt)
                        : "-"
        );
        steps.add(applied);

        LeaveRequestResponse.WorkflowStep manager =
                new LeaveRequestResponse.WorkflowStep();

        manager.setStep("Manager Review");
        manager.setBy(
                r.getApprovedBy() != null
                        ? r.getApprovedBy().getFirstName()
                        : "Manager"
        );
        manager.setRole("Manager");

        if ("Approved".equalsIgnoreCase(r.getStatus())) {
            manager.setStatus("completed");
            manager.setTime(r.getApprovedAt().format(fmt));
        } else if ("Rejected".equalsIgnoreCase(r.getStatus())) {
            manager.setStatus("rejected");
            manager.setTime(r.getApprovedAt().format(fmt));
            manager.setComment(r.getRejectionReason());
        } else if ("Withdrawn".equalsIgnoreCase(r.getStatus())) {
            manager.setStatus("withdrawn");
            manager.setTime("-");
        } else {
            manager.setStatus("pending");
            manager.setTime("-");
        }

        steps.add(manager);
        return steps;
    }

    @Override
    public LeaveRequestResponse withdrawLeave(Long employeeId, Long leaveId) {

        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveId)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));

        if (!leaveRequest.getEmployee().getId().equals(employeeId)) {
            throw new RuntimeException("You cannot withdraw this leave");
        }

        if (!"Pending".equalsIgnoreCase(leaveRequest.getStatus())) {
            throw new RuntimeException("Only pending leaves can be withdrawn");
        }

        leaveRequest.setStatus("Withdrawn");
        leaveRequestRepository.save(leaveRequest);

        return mapToResponse(leaveRequest);
    }
}