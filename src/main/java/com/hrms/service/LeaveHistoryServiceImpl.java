package com.hrms.service;

import com.hrms.dto.LeaveHistoryResponse;
import com.hrms.dto.LeaveHistoryResponse.WorkflowNode;
import com.hrms.repository.LeaveRequestRepository;
import org.springframework.stereotype.Service;


import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveHistoryServiceImpl implements LeaveHistoryService {

    private final LeaveRequestRepository leaveRepo;

    public LeaveHistoryServiceImpl(LeaveRequestRepository leaveRepo) {
        this.leaveRepo = leaveRepo;
    }

    @Override
    public List<LeaveHistoryResponse> getLeaveHistory(Long employeeId) {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd MMM yyyy");

        return leaveRepo.findByEmployeeWithApprover(employeeId).stream().map(l -> {

            LeaveHistoryResponse r = new LeaveHistoryResponse();
            r.setId(l.getId());
            r.setType(l.getLeaveType());
            r.setReason(l.getReason());
            r.setDays(l.getTotalDays());
            r.setDates(
                l.getStartDate().format(df) + " - " + l.getEndDate().format(df)
            );
            r.setRequestedOn(l.getAppliedAt().format(df));
            r.setStatus(l.getStatus());

            if ("Pending".equalsIgnoreCase(l.getStatus())) {
            	r.setPendingWith(
            		    l.getEmployee().getManager() != null
            		        ? l.getEmployee().getManager()
            		        : "HR"
               );
            }

            r.setApprovedBy(
                l.getApprovedBy() != null
                    ? l.getApprovedBy().getFirstName()
                    : "-"
            );

            r.setWorkflow(List.of(
                createNode("Leave Applied", l.getEmployee().getFirstName(),
                        "Employee", l.getAppliedAt().format(df),
                        "completed", null),

                createNode("Manager Approval",
                        l.getApprovedBy() != null ? l.getApprovedBy().getFirstName() : "Manager",
                        "Manager",
                        l.getApprovedAt() != null ? l.getApprovedAt().format(df) : "-",
                        mapStatus(l.getStatus()),
                        l.getStatus().equals("Rejected") ? l.getRejectionReason() : null)
            ));

            return r;
        }).collect(Collectors.toList());
    }

    private WorkflowNode createNode(String step, String by, String role,
                                    String time, String status, String comment) {

        WorkflowNode n = new WorkflowNode();
        n.setStep(step);
        n.setBy(by);
        n.setRole(role);
        n.setTime(time);
        n.setStatus(status);
        n.setComment(comment);
        return n;
    }

    private String mapStatus(String status) {
        if ("Approved".equalsIgnoreCase(status)) return "completed";
        if ("Rejected".equalsIgnoreCase(status)) return "rejected";
        return "pending";
    }
}
