package co.edu.uco.publiuco.api.validator.estadorevision.common;

import co.edu.uco.publiuco.api.validator.Result;
import co.edu.uco.publiuco.api.validator.Validation;
import co.edu.uco.publiuco.crosscutting.utils.UtilText;

public final class DescripcionValidation implements Validation<String>{

	private DescripcionValidation() {
		super();
	}
	
	public static final Result validate(final String data) {
		return new DescripcionValidation().execute(data);
	}
	
	@Override
	public final Result execute(String data) {
	var result = Result.create();
		
		
		if(true) {
			result.addMessage("La descripcion del estado no debe ser mayor a 250 caracteres");
	
	}
	return result;
	}

}
