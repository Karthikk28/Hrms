package com.hrms.service;

import com.hrms.dto.LeaveHistoryResponse;
import java.util.List;

public interface LeaveHistoryService {
    List<LeaveHistoryResponse> getLeaveHistory(Long employeeId);
}
