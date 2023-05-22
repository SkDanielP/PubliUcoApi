package co.edu.uco.publiuco.api.validator.estadoescritor;

import co.edu.uco.publiuco.api.validator.Result;
import co.edu.uco.publiuco.api.validator.Validation;
import co.edu.uco.publiuco.api.validator.estadotiporelacioninstitucion.common.DescripcionValidation;
import co.edu.uco.publiuco.api.validator.estadotiporelacioninstitucion.common.nombreValidation;
import co.edu.uco.publiuco.crosscutting.utils.UtilObject;
import co.edu.uco.publiuco.dto.EstadoAdministradorCategoriaDTO;
import co.edu.uco.publiuco.dto.EstadoCategoriaDTO;
import co.edu.uco.publiuco.dto.EstadoEscritorDTO;
import co.edu.uco.publiuco.dto.EstadoTipoRelacionInstitucionDTO;

public final class RegistrarEstadoEscritorValidation implements Validation<EstadoEscritorDTO> {

	private RegistrarEstadoEscritorValidation() {
		super();
	}
	
	public static final Result validate(final EstadoEscritorDTO data){
		return new RegistrarEstadoEscritorValidation().execute(data);
	}
	
	@Override
	public Result execute(final EstadoEscritorDTO data) {
		var result = Result.create();
		
		if(UtilObject.isNull(data)) {
			result.addMessage("No es posible registrar un nuevo estado con los datos vacios...");
		}else {
			
		
		
		result.addMessages(nombreValidation.validate(data.getNombre()).getMessages());
		result.addMessages(DescripcionValidation.validate(data.getDescripcion()).getMessages());
		}
		
		return result;
		
	}

}
