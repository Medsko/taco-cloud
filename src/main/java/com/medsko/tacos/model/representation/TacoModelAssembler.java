package com.medsko.tacos.model.representation;

import com.medsko.tacos.controllers.TacoController;
import com.medsko.tacos.model.Taco;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class TacoModelAssembler extends RepresentationModelAssemblerSupport<Taco, TacoModel> {

	public TacoModelAssembler() {
		super(TacoController.class, TacoModel.class);
	}

	@Override
	public TacoModel toModel(Taco taco) {
		return createModelWithId(taco.getId(), taco);
	}

	@Override
	protected TacoModel instantiateModel(Taco taco) {
		return new TacoModel(taco);
	}
}
