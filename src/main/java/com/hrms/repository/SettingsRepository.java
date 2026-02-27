package com.hrms.repository;

import com.hrms.entity.Settings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SettingsRepository extends JpaRepository<Settings, Integer> {
    Optional<Settings> findByEmployeeId(Integer employeeId);
    void deleteByEmployeeId(Integer employeeId);
}
