package com.hrms.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "leave_balances")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LeaveBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;

    @Column(name = "leave_type")
    private String leaveType;

    @Column(name = "code")
    private String code;

    private Float balance;
    private Float total;

    @Column(name = "bg")
    private String bg;

    public Long getId() { return id; }
    public Employee getEmployee() { return employee; }
    public String getLeaveType() { return leaveType; }
    public String getCode() { return code; }
    public Float getBalance() { return balance; }
    public Float getTotal() { return total; }
    public String getBg() { return bg; }

    public void setId(Long id) { this.id = id; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public void setLeaveType(String leaveType) { this.leaveType = leaveType; }
    public void setCode(String code) { this.code = code; }
    public void setBalance(Float balance) { this.balance = balance; }
    public void setTotal(Float total) { this.total = total; }
    public void setBg(String bg) { this.bg = bg; }
}
