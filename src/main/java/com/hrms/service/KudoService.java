package com.hrms.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hrms.entity.Kudo;
import com.hrms.repository.KudoRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class KudoService {

	private final KudoRepository kudoRepo;

	public KudoService(KudoRepository kudoRepo) {
		this.kudoRepo = kudoRepo;
	}

	public List<Kudo> getAllKudos() {
		return kudoRepo.findAll(Sort.by(Sort.Direction.DESC, "time"));
	}

	public Kudo addKudo(Kudo kudo) {
		System.out.println("KUDO RECEIVED: " + kudo.getMsg());
		kudo.setTime(LocalDateTime.now());
		return kudoRepo.save(kudo);

	}
}
