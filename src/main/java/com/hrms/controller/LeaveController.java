package com.hrms.controller;

import com.hrms.dto.ApplyLeaveRequestResponse;
import com.hrms.dto.LeaveRequestResponse;
import com.hrms.entity.LeaveBalance;
import com.hrms.service.LeaveRequestService;
import com.hrms.service.LeaveService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/employees/{employeeId}/leaves")
public class LeaveController {

    private final LeaveService leaveService;
    private final LeaveRequestService leaveRequestService;

    public LeaveController(
            LeaveService leaveService,
            LeaveRequestService leaveRequestService
    ) {
        this.leaveService = leaveService;
        this.leaveRequestService = leaveRequestService;
    }

    @GetMapping("/balances")
    public List<LeaveBalance> getBalances(@PathVariable Long employeeId) {
        return leaveService.getLeaveBalances(employeeId);
    }

    @GetMapping("/history")
    public List<LeaveRequestResponse> getHistory(@PathVariable Long employeeId) {
        return leaveRequestService.getLeaveRequests(employeeId);
    }

    @PostMapping(value = "/apply", consumes = "multipart/form-data")
    public LeaveRequestResponse applyLeave(
            @PathVariable Long employeeId,
            @ModelAttribute ApplyLeaveRequestResponse request,
            @RequestPart(required = false) MultipartFile file
    ) {
        return leaveRequestService.applyLeave(employeeId, request, file);
    }

    @GetMapping("/total")
    public Float getYearlyTotal(
            @PathVariable Long employeeId,
            @RequestParam Integer year
    ) {
        return leaveService.getYearlyTotal(employeeId, year);
    }

    @PostMapping("/{leaveId}/withdraw")
    public LeaveRequestResponse withdrawLeave(
            @PathVariable Long employeeId,
            @PathVariable Long leaveId
    ) {
        return leaveRequestService.withdrawLeave(employeeId, leaveId);
    }
}