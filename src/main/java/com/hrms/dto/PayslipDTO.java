package com.hrms.dto;

import java.util.List;
import com.hrms.entity.Deduction;
import com.hrms.entity.Earning;
import com.hrms.entity.Employee;

public class PayslipDTO {

    private String id;
    private String month;
    private String year;
    private Long netPay;
    private String status;

    private List<Earning> earnings;
    private List<Deduction> deductions;
    private PayslipStats stats;
    private Employee employee;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public Long getNetPay() { return netPay; }
    public void setNetPay(Long netPay) { this.netPay = netPay; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<Earning> getEarnings() { return earnings; }
    public void setEarnings(List<Earning> earnings) { this.earnings = earnings; }

    public List<Deduction> getDeductions() { return deductions; }
    public void setDeductions(List<Deduction> deductions) { this.deductions = deductions; }

    public PayslipStats getStats() { return stats; }
    public void setStats(PayslipStats stats) { this.stats = stats; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
}
