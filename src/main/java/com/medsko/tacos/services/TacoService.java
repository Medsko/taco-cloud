package com.medsko.tacos.services;

import com.medsko.tacos.model.Taco;
import com.medsko.tacos.repositories.TacoRepository;
import org.springframework.stereotype.Service;

@Service
public class TacoService {

	private final TacoRepository tacoRepository;

	public TacoService(TacoRepository tacoRepository) {
		this.tacoRepository = tacoRepository;
	}

	public Taco save(Taco taco) {
		return tacoRepository.save(taco);
	}
}
