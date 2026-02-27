package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.Earning;

@Repository
public interface EarningsRepository extends JpaRepository<Earning, Integer> {
    List<Earning> findByPayslipId(String payslipId);
}

