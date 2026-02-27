package com.hrms.entity;

import java.time.LocalDate;
import java.util.List;

import com.hrms.dto.PayslipStats;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "payslip")
public class Payslip {

    @Id
    private String id;

    private Integer userId;
    private String month;
    private String year;
    private LocalDate creditDate;
    private Long netPay;
    private String status;

    @Transient
    private List<Earning> earnings;

    @Transient
    private List<Deduction> deductions;

    @Transient
    private PayslipStats stats;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public LocalDate getCreditDate() {
        return creditDate;
    }

    public void setCreditDate(LocalDate creditDate) {
        this.creditDate = creditDate;
    }

    public Long getNetPay() {
        return netPay;
    }

    public void setNetPay(Long netPay) {
        this.netPay = netPay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Earning> getEarnings() {
        return earnings;
    }

    public void setEarnings(List<Earning> earnings) {
        this.earnings = earnings;
    }

    public List<Deduction> getDeductions() {
        return deductions;
    }

    public void setDeductions(List<Deduction> deductions) {
        this.deductions = deductions;
    }

    public PayslipStats getStats() {
        return stats;
    }

    public void setStats(PayslipStats stats2) {
        this.stats = stats2;
    }
}
