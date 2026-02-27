package com.hrms.dto;

public class AttendanceStatsResponse {

    private Double averageHours;
    private int lateDays;
    private int earlyLeaves;

    public Double getAverageHours() { return averageHours; }
    public void setAverageHours(Double averageHours) { this.averageHours = averageHours; }

    public int getLateDays() { return lateDays; }
    public void setLateDays(int lateDays) { this.lateDays = lateDays; }

    public int getEarlyLeaves() { return earlyLeaves; }
    public void setEarlyLeaves(int earlyLeaves) { this.earlyLeaves = earlyLeaves; }
}