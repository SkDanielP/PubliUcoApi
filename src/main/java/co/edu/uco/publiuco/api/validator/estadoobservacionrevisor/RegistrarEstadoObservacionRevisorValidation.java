package co.edu.uco.publiuco.api.validator.estadoobservacionrevisor;

import co.edu.uco.publiuco.api.validator.Result;
import co.edu.uco.publiuco.api.validator.Validation;
import co.edu.uco.publiuco.api.validator.estadotiporelacioninstitucion.common.DescripcionValidation;
import co.edu.uco.publiuco.api.validator.estadotiporelacioninstitucion.common.nombreValidation;
import co.edu.uco.publiuco.crosscutting.utils.UtilObject;
import co.edu.uco.publiuco.dto.EstadoAdministradorCategoriaDTO;
import co.edu.uco.publiuco.dto.EstadoCategoriaDTO;
import co.edu.uco.publiuco.dto.EstadoObservacionRevisorDTO;
import co.edu.uco.publiuco.dto.EstadoTipoRelacionInstitucionDTO;

public final class RegistrarEstadoObservacionRevisorValidation implements Validation<EstadoObservacionRevisorDTO> {

	private RegistrarEstadoObservacionRevisorValidation() {
		super();
	}
	
	public static final Result validate(final EstadoObservacionRevisorDTO data){
		return new RegistrarEstadoObservacionRevisorValidation().execute(data);
	}
	
	@Override
	public Result execute(final EstadoObservacionRevisorDTO data) {
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
