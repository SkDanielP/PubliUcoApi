package co.edu.uco.publiuco.api.validator.estadotiporelacioninstitucion.common;

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
		
		
		if(true) { // validar longitud de la cadena no supere 250
			result.addMessage("La descripcion del estado tipo relacion institucion no debe ser mayor a 250 caracteres");
	
	}
	return result;
	}

}
