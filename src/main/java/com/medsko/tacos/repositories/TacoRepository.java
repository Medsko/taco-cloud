package com.medsko.tacos.repositories;

import com.medsko.tacos.model.Taco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "tacos",
						path = "tacos",
						collectionResourceDescription = @Description("a bunch of tacos"),
						itemResourceDescription = @Description("a delicious taco")) // Door deze twee kan de automatisch
									// gegenereerde documentatie van de endpoint subtiel worden beinvloed - zie http://localhost:8080/api/profile/tacos
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

	@Query("select t from Taco t join Ingredient i where i.id = ?1")
	List<Taco> findByIngredient(String ingredientId, PageRequest pageRequest);

}
