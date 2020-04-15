package com.medsko.tacos.controllers;

import com.medsko.tacos.model.Taco;
import com.medsko.tacos.model.representation.TacoModel;
import com.medsko.tacos.model.representation.TacoModelAssembler;
import com.medsko.tacos.repositories.TacoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RepositoryRestController
@RequestMapping("/tacos")
public class TacoController {

	private final TacoRepository tacoRepository;

	public TacoController(TacoRepository tacoRepository) {
		this.tacoRepository = tacoRepository;
	}

	@GetMapping(path = "/recent", produces = "application/hal+json")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public CollectionModel<TacoModel> recentTacos() {

		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		CollectionModel<TacoModel> recentTacos = new TacoModelAssembler().toCollectionModel(tacoRepository.findAll(page));

		recentTacos.add(
				linkTo(methodOn(TacoController.class).recentTacos()).withRel("recents"));

		return recentTacos;
	}

	@GetMapping("/with-ingredient/{ingredientId}")
	public CollectionModel<TacoModel> getByIngredient(@PathVariable("ingredientId") String ingredientId) {
		PageRequest page = PageRequest.of(0, 5);
		return new TacoModelAssembler().toCollectionModel(tacoRepository.findByIngredient(ingredientId, page));
	}

	@Bean
	public RepresentationModelProcessor<RepresentationModel<TacoModel>> tacoModelProcessor(EntityLinks links) {
		return model -> {
				model.add(
						links.linkFor(Taco.class)
							.slash("recent")
							.withRel("recents")
				);
				return model;
		};
	}
}
