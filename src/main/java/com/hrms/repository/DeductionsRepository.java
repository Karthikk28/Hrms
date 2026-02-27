package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.Deduction;

@Repository
public interface DeductionsRepository extends JpaRepository<Deduction, Integer> {
    List<Deduction> findByPayslipId(String payslipId);
}

