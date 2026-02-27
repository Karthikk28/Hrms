package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.Payslip;

@Repository
public interface PayslipRepository extends JpaRepository<Payslip, String> {
    List<Payslip> findByUserIdAndYear(Integer userId, String year);
}

