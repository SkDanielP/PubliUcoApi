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

import co.edu.uco.business.facade.EstadoEscritorFacade;
import co.edu.uco.business.facade.EstadoPlanFacade;
import co.edu.uco.business.facade.EstadoPublicacionFacade;
import co.edu.uco.business.facade.EstadoTipoRelacionInstitucionFacade;
import co.edu.uco.business.facade.impl.EstadoEscritorFacadeImpl;
import co.edu.uco.business.facade.impl.EstadoPlanFacadeImpl;
import co.edu.uco.business.facade.impl.EstadoPublicacionFacadeImpl;
import co.edu.uco.business.facade.impl.EstadoTipoRelacionFacadeImpl;
import co.edu.uco.publiuco.api.controller.response.Response;
import co.edu.uco.publiuco.api.validator.estadoescritor.RegistrarEstadoEscritorValidation;
import co.edu.uco.publiuco.api.validator.estadoplan.RegistrarEstadoPlanValidation;
import co.edu.uco.publiuco.api.validator.estadopublicacion.RegistrarEstadoPublicacionValidation;
import co.edu.uco.publiuco.api.validator.estadotiporelacioninstitucion.RegistrarEstadoTipoRelacionInstitucionValidation;
import co.edu.uco.publiuco.crosscutting.exception.PubliucoException;
import co.edu.uco.publiuco.dto.EstadoEscritorDTO;
import co.edu.uco.publiuco.dto.EstadoPlanDTO;
import co.edu.uco.publiuco.dto.EstadoPublicacionDTO;
import co.edu.uco.publiuco.dto.EstadoTipoRelacionInstitucionDTO;

@RestController
@RequestMapping("publiuco/api/v1/estadotiporelacioninstitucion")
public final class EstadoPublicacionController {
	
	
	private EstadoPublicacionFacade facade;
	
	public EstadoPublicacionController() {
		facade = new EstadoPublicacionFacadeImpl();
	}

	@GetMapping("/dummy")
	public EstadoPublicacionDTO dummy() {
		return EstadoPublicacionDTO.create();
	}
	
	@GetMapping
	public ResponseEntity<Response<EstadoPublicacionDTO>> list(@RequestParam EstadoPublicacionDTO dto) {
		
		
		List<EstadoPublicacionDTO> lista = new ArrayList<>();
		lista.add(EstadoPublicacionDTO.create()); 
		lista.add(EstadoPublicacionDTO.create()); 
		lista.add(EstadoPublicacionDTO.create()); 
		lista.add(EstadoPublicacionDTO.create()); 
		lista.add(EstadoPublicacionDTO.create()); 
		
		List<String> messages = new ArrayList<>();
		messages.add("Estados de tipo relacion institucion consultados  exitosamente");
 		
		Response<EstadoPublicacionDTO> response = new Response<>(lista, messages);
		
		return new ResponseEntity<Response<EstadoPublicacionDTO>>
		(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public EstadoPublicacionDTO listById(@PathVariable UUID id){
		return EstadoPublicacionDTO.create().setIdentificador(id);
	}
	
	@PostMapping
	public ResponseEntity<Response<EstadoPublicacionDTO>> create(@RequestParam EstadoPublicacionDTO dto) {
		
		var statusCode = HttpStatus.OK;
		var response = new Response<EstadoPublicacionDTO>();
		
		try {
			var result = RegistrarEstadoPublicacionValidation.validate(dto);
			
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
	public EstadoPublicacionDTO update(@PathVariable UUID id, @RequestParam EstadoPublicacionDTO dto) {
		return dto.setIdentificador(id);
	}
	
	@DeleteMapping("/{id}")
	public EstadoPublicacionDTO delete(@PathVariable UUID id) {
		return EstadoPublicacionDTO.create().setIdentificador(id);
	}
}