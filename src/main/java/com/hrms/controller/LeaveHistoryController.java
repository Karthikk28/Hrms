package com.hrms.controller;

import com.hrms.dto.LeaveHistoryResponse;
import com.hrms.service.LeaveHistoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@CrossOrigin
public class LeaveHistoryController {

    private final LeaveHistoryService leaveHistoryService;

    public LeaveHistoryController(LeaveHistoryService leaveHistoryService) {
        this.leaveHistoryService = leaveHistoryService;
    }

    @GetMapping("/history/{employeeId}")
    public List<LeaveHistoryResponse> getLeaveHistory(
            @PathVariable Long employeeId) {

        return leaveHistoryService.getLeaveHistory(employeeId);
    }
}
