package co.edu.uco.publiuco.api.validator.estadotipoescritor.common;

import co.edu.uco.publiuco.api.validator.Result;
import co.edu.uco.publiuco.api.validator.Validation;
import co.edu.uco.publiuco.crosscutting.utils.UtilObject;
import co.edu.uco.publiuco.crosscutting.utils.UtilText;
import co.edu.uco.publiuco.crosscutting.utils.UtilUUID;

public final class nombreValidation implements Validation<String>{

	
	private nombreValidation() {
		super();
	}
	
	public static final Result validate(final String data) {
		return new nombreValidation().execute(data);
	}
	
	@Override
	public final Result execute(String data) {
		var result = Result.create();
		
		if(UtilText.getUtilText().isEmpty(data)) {
			result.addMessage("No es posible continuar con el nombre del estado vacio");
		}
		
		else {
			
			if(true)
				result.addMessage("El nombre del estado solo debe tener letras y espacios");
			
			if(true) { //validar longitud de la cadena entre 1 y 30
			result.addMessage("El nombre del estado debe tener una longitud entre 1 y 30");
		}
		}
		return result;
	}

}
