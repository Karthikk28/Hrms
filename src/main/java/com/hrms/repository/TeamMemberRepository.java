package com.hrms.repository;

import com.hrms.repository.TeamMemberRepository;
import com.hrms.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrms.entity.TeamMember;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
	  void deleteByEmployeeId(Long employeeId);
}
