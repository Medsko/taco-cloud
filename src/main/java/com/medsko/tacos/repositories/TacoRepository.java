package com.medsko.tacos.repositories;

import com.medsko.tacos.model.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
