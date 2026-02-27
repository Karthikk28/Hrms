package com.hrms.dto;

import java.time.LocalTime;

public class ShiftResponse {

    private String shiftName;
    private LocalTime startTime;
    private LocalTime endTime;

    public ShiftResponse() {}

    public String getShiftName() { return shiftName; }
    public void setShiftName(String shiftName) { this.shiftName = shiftName; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
}
