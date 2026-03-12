package com.hrms.controller;

import org.springframework.web.bind.annotation.*;
import com.hrms.entity.Kudo;
import com.hrms.service.KudoService;
import java.util.List;

@RestController
@RequestMapping("/api/kudos")
@CrossOrigin(origins = "http://localhost:3000")
public class KudoController {

	private final KudoService kudoService;

	public KudoController(KudoService kudoService) {
		this.kudoService = kudoService;
	}

	@GetMapping
	public List<Kudo> getKudos() {
		return kudoService.getAllKudos();
	}

	@PostMapping("/add")
	public Kudo addKudo(@RequestBody Kudo kudo) {
		return kudoService.addKudo(kudo);
	}
}
