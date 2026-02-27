package com.hrms.entity;

public class PayslipStats {

    private int gross;
    private int totalDeductions;
    private int netPay;
    private int payableDays;
    private int lopDays;

    public PayslipStats() {}

    public PayslipStats(int gross, int totalDeductions, int netPay, int payableDays, int lopDays) {
        this.gross = gross;
        this.totalDeductions = totalDeductions;
        this.netPay = netPay;
        this.payableDays = payableDays;
        this.lopDays = lopDays;
    }

    public int getGross() { return gross; }
    public void setGross(int gross) { this.gross = gross; }

    public int getTotalDeductions() { return totalDeductions; }
    public void setTotalDeductions(int totalDeductions) { this.totalDeductions = totalDeductions; }

    public int getNetPay() { return netPay; }
    public void setNetPay(int netPay) { this.netPay = netPay; }

    public int getPayableDays() { return payableDays; }
    public void setPayableDays(int payableDays) { this.payableDays = payableDays; }

    public int getLopDays() { return lopDays; }
    public void setLopDays(int lopDays) { this.lopDays = lopDays; }
}
