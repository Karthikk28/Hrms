package com.hrms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "payslip_earnings")
public class Earning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "payslip_id")
    private String payslipId;

    private String label;

    private Long amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayslipId() {
        return payslipId;
    }

    public void setPayslipId(String payslipId) {
        this.payslipId = payslipId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
