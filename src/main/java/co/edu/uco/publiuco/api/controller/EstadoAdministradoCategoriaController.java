package co.edu.uco.publiuco.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.business.facade.EstadoAdministradorCategoriaFacade;
import co.edu.uco.business.facade.EstadoTipoRelacionInstitucionFacade;
import co.edu.uco.business.facade.impl.EstadoAdministradorCategoriaFacadeImpl;
import co.edu.uco.business.facade.impl.EstadoTipoRelacionFacadeImpl;
import co.edu.uco.publiuco.api.controller.response.Response;
import co.edu.uco.publiuco.api.validator.estadoadministradorcategoria.RegistrarEstadoAdmnistradorCategoriaValidation;
import co.edu.uco.publiuco.api.validator.estadotiporelacioninstitucion.RegistrarEstadoTipoRelacionInstitucionValidation;
import co.edu.uco.publiuco.crosscutting.exception.PubliucoException;
import co.edu.uco.publiuco.dto.EstadoAdministradorCategoriaDTO;
import co.edu.uco.publiuco.dto.EstadoTipoRelacionInstitucionDTO;

@RestController
@RequestMapping("publiuco/api/v1/estadotiporelacioninstitucion")
public final class EstadoAdministradoCategoriaController {
	
	
	private EstadoAdministradorCategoriaFacade facade;
	
	public EstadoAdministradoCategoriaController() {
		facade = new EstadoAdministradorCategoriaFacadeImpl();
	}

	@GetMapping("/dummy")
	public EstadoAdministradorCategoriaDTO dummy() {
		return EstadoAdministradorCategoriaDTO.create();
	}
	
	@GetMapping
	public ResponseEntity<Response<EstadoAdministradorCategoriaDTO>> list(@RequestParam EstadoAdministradorCategoriaDTO dto) {
		
		
		List<EstadoAdministradorCategoriaDTO> lista = new ArrayList<>();
		lista.add(EstadoAdministradorCategoriaDTO.create()); 
		lista.add(EstadoAdministradorCategoriaDTO.create()); 
		lista.add(EstadoAdministradorCategoriaDTO.create()); 
		lista.add(EstadoAdministradorCategoriaDTO.create()); 
		lista.add(EstadoAdministradorCategoriaDTO.create()); 
		
		List<String> messages = new ArrayList<>();
		messages.add("Estados de tipo relacion institucion consultados  exitosamente");
 		
		Response<EstadoAdministradorCategoriaDTO> response = new Response<>(lista, messages);
		
		return new ResponseEntity<Response<EstadoAdministradorCategoriaDTO>>
		(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public EstadoAdministradorCategoriaDTO listById(@PathVariable UUID id){
		return EstadoAdministradorCategoriaDTO.create().setIdentificador(id);
	}
	
	@PostMapping
	public ResponseEntity<Response<EstadoAdministradorCategoriaDTO>> create(@RequestParam EstadoAdministradorCategoriaDTO dto) {
		
		var statusCode = HttpStatus.OK;
		var response = new Response<EstadoAdministradorCategoriaDTO>();
		
		try {
			var result = RegistrarEstadoAdmnistradorCategoriaValidation.validate(dto);
			
			if(result.getMessages().isEmpty()) {
				facade.register(dto);
				response.getMessages().add("El nuevo tipo relacion institucion se ha registrado");
				
			}else {
				statusCode = HttpStatus.BAD_REQUEST;
				response.setMessages(result.getMessages());
				

			}
				
		} catch (final PubliucoException exception) {
			statusCode = HttpStatus.BAD_REQUEST;
			response.getMessages().add(exception.getUserMessage());
			System.err.println(exception.getTechnicalMessage());
			System.err.println(exception.getType());
			exception.printStackTrace();
		}catch (final Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add("Se ha presentado un problema inesperado. por favor intentar de nuevo y si el problema persiste contacte al administrador de la aplicacion");
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}
		
		
		return new ResponseEntity<>(response, statusCode) ;
	}
	
	@PutMapping("/{id}")
	public EstadoAdministradorCategoriaDTO update(@PathVariable UUID id, @RequestParam EstadoAdministradorCategoriaDTO dto) {
		return dto.setIdentificador(id);
	}
	
	@DeleteMapping("/{id}")
	public EstadoAdministradorCategoriaDTO delete(@PathVariable UUID id) {
		return EstadoAdministradorCategoriaDTO.create().setIdentificador(id);
	}
}