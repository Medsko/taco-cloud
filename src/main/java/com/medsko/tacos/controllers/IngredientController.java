package com.medsko.tacos.controllers;

import com.medsko.tacos.model.representation.IngredientModel;
import com.medsko.tacos.model.representation.IngredientModelAssembler;
import com.medsko.tacos.repositories.IngredientRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RepositoryRestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final IngredientRepository repository;

    public IngredientController(IngredientRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public CollectionModel<IngredientModel> listIngredients() {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("type", "name"));
        return new IngredientModelAssembler().toCollectionModel(repository.findAll(pageRequest));
    }

}
