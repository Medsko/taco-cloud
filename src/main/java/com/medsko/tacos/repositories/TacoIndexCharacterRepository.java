package com.medsko.tacos.repositories;

import com.medsko.tacos.model.TacoIndexCharacter;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoIndexCharacterRepository extends PagingAndSortingRepository<TacoIndexCharacter, Long> {

	// Awww yeah, this shows up in the actual JSON (when querying http://localhost:8080/api/tacoIndexCharacters/search)!!!
	TacoIndexCharacter findSchluppyDuppyConquererByIdGreaterThanEqual(Long id);
}
