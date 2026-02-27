package com.hrms.dto;

import java.time.LocalDateTime;

public class DailyAttendanceResponse {

    private LocalDateTime punchIn;
    private LocalDateTime punchOut;
    private Double totalHours;
    private String status;

    public LocalDateTime getPunchIn() { return punchIn; }
    public void setPunchIn(LocalDateTime punchIn) { this.punchIn = punchIn; }

    public LocalDateTime getPunchOut() { return punchOut; }
    public void setPunchOut(LocalDateTime punchOut) { this.punchOut = punchOut; }

    public Double getTotalHours() { return totalHours; }
    public void setTotalHours(Double totalHours) { this.totalHours = totalHours; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}