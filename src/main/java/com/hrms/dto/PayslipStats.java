package com.hrms.dto;

public class PayslipStats {
    private long gross;
    private long totalDeductions;
    private long netPay;
    private int payableDays;
    private int lopDays;

    public long getGross() { return gross; }
    public void setGross(long gross) { this.gross = gross; }

    public long getTotalDeductions() { return totalDeductions; }
    public void setTotalDeductions(long totalDeductions) { this.totalDeductions = totalDeductions; }

    public long getNetPay() { return netPay; }
    public void setNetPay(long netPay) { this.netPay = netPay; }

    public int getPayableDays() { return payableDays; }
    public void setPayableDays(int payableDays) { this.payableDays = payableDays; }

    public int getLopDays() { return lopDays; }
    public void setLopDays(int lopDays) { this.lopDays = lopDays; }
}
