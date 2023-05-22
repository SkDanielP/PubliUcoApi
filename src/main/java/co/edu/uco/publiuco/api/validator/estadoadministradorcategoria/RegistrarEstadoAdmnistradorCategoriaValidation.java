package co.edu.uco.publiuco.api.validator.estadoadministradorcategoria;

import co.edu.uco.publiuco.api.validator.Result;
import co.edu.uco.publiuco.api.validator.Validation;
import co.edu.uco.publiuco.api.validator.estadotiporelacioninstitucion.common.DescripcionValidation;
import co.edu.uco.publiuco.api.validator.estadotiporelacioninstitucion.common.nombreValidation;
import co.edu.uco.publiuco.crosscutting.utils.UtilObject;
import co.edu.uco.publiuco.dto.EstadoAdministradorCategoriaDTO;
import co.edu.uco.publiuco.dto.EstadoTipoRelacionInstitucionDTO;

public final class RegistrarEstadoAdmnistradorCategoriaValidation implements Validation<EstadoAdministradorCategoriaDTO> {

	private RegistrarEstadoAdmnistradorCategoriaValidation() {
		super();
	}
	
	public static final Result validate(final EstadoAdministradorCategoriaDTO data){
		return new RegistrarEstadoAdmnistradorCategoriaValidation().execute(data);
	}
	
	@Override
	public Result execute(final EstadoAdministradorCategoriaDTO data) {
		var result = Result.create();
		
		if(UtilObject.isNull(data)) {
			result.addMessage("No es posible registrar un nuevo tipo estado administrador con los datos vacios...");
		}else {
			
		
		
		result.addMessages(nombreValidation.validate(data.getNombre()).getMessages());
		result.addMessages(DescripcionValidation.validate(data.getDescripcion()).getMessages());
		}
		
		return result;
		
	}

}
