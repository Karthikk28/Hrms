package com.hrms.service;

import org.springframework.stereotype.Service;

import com.hrms.entity.TeamMember;
import com.hrms.repository.TeamMemberRepository;

import java.util.List;

@Service
public class TeamMemberService {

	private final TeamMemberRepository memberRepo;

	public TeamMemberService(TeamMemberRepository memberRepo) {
		this.memberRepo = memberRepo;
	}

	public List<TeamMember> getAllMembers() {
		return memberRepo.findAll();
	}

	public TeamMember addMember(TeamMember member) {
		System.out.println("Saving member: " + member.getName());
		return memberRepo.save(member);

	}
	public TeamMember updateStatus(Long id, String status) {
	    TeamMember member = memberRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Team member not found"));

	    member.setStatus(status);
	    return memberRepo.save(member);
	}
}
