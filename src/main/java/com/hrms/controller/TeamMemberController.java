package com.hrms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hrms.entity.Employee;
import com.hrms.entity.TeamMember;
import com.hrms.service.TeamMemberService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/team")
@CrossOrigin(origins = "http://localhost:3000")
public class TeamMemberController {

	private final TeamMemberService memberService;

	public TeamMemberController(TeamMemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping
	public List<TeamMember> getAllMembers() {
		return memberService.getAllMembers();
	}

	@PostMapping
	public TeamMember addMember(@RequestBody TeamMember member) {
		return memberService.addMember(member);
	}
	@PutMapping("/{id}/status")
	public ResponseEntity<TeamMember> updateStatus(
	        @PathVariable Long id,
	        @RequestBody Map<String, String> body
	) {
	    String status = body.get("status");
	    return ResponseEntity.ok(memberService.updateStatus(id, status));
	}
}
