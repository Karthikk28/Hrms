package com.hrms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrms.dto.PayslipDTO;
import com.hrms.dto.PayslipStats;
import com.hrms.entity.Deduction;
import com.hrms.entity.Earning;
import com.hrms.entity.Employee;
import com.hrms.entity.Payslip;
import com.hrms.repository.DeductionsRepository;
import com.hrms.repository.EarningsRepository;
import com.hrms.repository.EmployeeRepository;
import com.hrms.repository.PayslipRepository;

@Service
public class PayslipService {

    @Autowired
    private PayslipRepository payslipRepository;

    @Autowired
    private EarningsRepository earningsRepository;

    @Autowired
    private DeductionsRepository deductionsRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Payslip> getPayslipHistory(Integer userId, String year) {
        return payslipRepository.findByUserIdAndYear(userId, year);
    }

    public PayslipDTO getPayslipById(String id) {
        Payslip slip = payslipRepository.findById(id).orElse(null);
        if (slip == null) return null;

        PayslipDTO dto = new PayslipDTO();
        dto.setId(slip.getId());
        dto.setMonth(slip.getMonth());
        dto.setYear(slip.getYear());
        dto.setNetPay(slip.getNetPay());
        dto.setStatus(slip.getStatus());

        List<Earning> earnings = earningsRepository.findByPayslipId(id);
        List<Deduction> deductions = deductionsRepository.findByPayslipId(id);
        dto.setEarnings(earnings);
        dto.setDeductions(deductions);

        PayslipStats stats = new PayslipStats();
        stats.setGross(earnings.stream().mapToLong(Earning::getAmount).sum());
        stats.setTotalDeductions(deductions.stream().mapToLong(Deduction::getAmount).sum());
        stats.setNetPay(slip.getNetPay());
        stats.setPayableDays(30);
        stats.setLopDays(0);
        dto.setStats(stats);

        Employee emp = employeeRepository.findById(slip.getUserId()).orElse(null);
        dto.setEmployee(emp);

        return dto;
    }
}
