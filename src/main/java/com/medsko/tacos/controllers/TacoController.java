package com.medsko.tacos.controllers;

import com.medsko.tacos.model.representation.TacoModel;
import com.medsko.tacos.model.representation.TacoModelAssembler;
import com.medsko.tacos.repositories.TacoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RepositoryRestController
public class TacoController {

	private final TacoRepository tacoRepository;

	public TacoController(TacoRepository tacoRepository) {
		this.tacoRepository = tacoRepository;
	}

	@GetMapping(path = "/tacos/recent", produces = "application/hal+json")
	public ResponseEntity<CollectionModel<TacoModel>> recentTacos() {

		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		CollectionModel<TacoModel> recentTacos = new TacoModelAssembler().toCollectionModel(tacoRepository.findAll(page));

		recentTacos.add(
				linkTo(methodOn(TacoController.class).recentTacos()).withRel("recents"));

		return new ResponseEntity<>(recentTacos, HttpStatus.OK);
	}

}
