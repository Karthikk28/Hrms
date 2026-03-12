package com.hrms.service;

import com.hrms.dto.ApplyLeaveRequestResponse;
import com.hrms.dto.LeaveRequestResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LeaveRequestService {

    List<LeaveRequestResponse> getLeaveRequests(Long employeeId);

    LeaveRequestResponse applyLeave(
            Long employeeId,
            ApplyLeaveRequestResponse request,
            MultipartFile file
    );
    
    LeaveRequestResponse withdrawLeave(Long employeeId, Long leaveId);
}