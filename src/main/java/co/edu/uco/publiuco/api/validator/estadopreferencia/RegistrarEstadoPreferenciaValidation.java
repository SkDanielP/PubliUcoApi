package co.edu.uco.publiuco.api.validator.estadopreferencia;

import co.edu.uco.publiuco.api.validator.Result;
import co.edu.uco.publiuco.api.validator.Validation;
import co.edu.uco.publiuco.api.validator.estadotiporelacioninstitucion.common.DescripcionValidation;
import co.edu.uco.publiuco.api.validator.estadotiporelacioninstitucion.common.nombreValidation;
import co.edu.uco.publiuco.crosscutting.utils.UtilObject;
import co.edu.uco.publiuco.dto.EstadoAdministradorCategoriaDTO;
import co.edu.uco.publiuco.dto.EstadoCategoriaDTO;
import co.edu.uco.publiuco.dto.EstadoEscritorDTO;
import co.edu.uco.publiuco.dto.EstadoLectorDTO;
import co.edu.uco.publiuco.dto.EstadoPreferenciaDTO;
import co.edu.uco.publiuco.dto.EstadoTipoRelacionInstitucionDTO;

public final class RegistrarEstadoPreferenciaValidation implements Validation<EstadoPreferenciaDTO> {

	private RegistrarEstadoPreferenciaValidation() {
		super();
	}
	
	public static final Result validate(final EstadoPreferenciaDTO data){
		return new RegistrarEstadoPreferenciaValidation().execute(data);
	}
	
	@Override
	public Result execute(final EstadoPreferenciaDTO data) {
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
