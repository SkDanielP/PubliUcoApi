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

import co.edu.uco.business.facade.EstadoCategoriaFacade;
import co.edu.uco.business.facade.EstadoObservacionRevisorFacade;
import co.edu.uco.business.facade.EstadoTipoRelacionInstitucionFacade;
import co.edu.uco.business.facade.impl.EstadoCategoriaFacadeImpl;
import co.edu.uco.business.facade.impl.EstadoObservacionRevisorFacadeImpl;
import co.edu.uco.business.facade.impl.EstadoTipoRelacionFacadeImpl;
import co.edu.uco.publiuco.api.controller.response.Response;
import co.edu.uco.publiuco.api.validator.estadocategoria.RegistrarEstadoCategoriaValidation;
import co.edu.uco.publiuco.api.validator.estadoobservacionrevisor.RegistrarEstadoObservacionRevisorValidation;
import co.edu.uco.publiuco.api.validator.estadotiporelacioninstitucion.RegistrarEstadoTipoRelacionInstitucionValidation;
import co.edu.uco.publiuco.crosscutting.exception.PubliucoException;
import co.edu.uco.publiuco.dto.EstadoCategoriaDTO;
import co.edu.uco.publiuco.dto.EstadoObservacionRevisorDTO;
import co.edu.uco.publiuco.dto.EstadoTipoRelacionInstitucionDTO;

@RestController
@RequestMapping("publiuco/api/v1/estadotiporelacioninstitucion")
public final class EstadoObservacionRevisorController {
	
	
	private EstadoObservacionRevisorFacade facade;
	
	public EstadoObservacionRevisorController() {
		facade = new EstadoObservacionRevisorFacadeImpl();
	}

	@GetMapping("/dummy")
	public EstadoObservacionRevisorDTO dummy() {
		return EstadoObservacionRevisorDTO.create();
	}
	
	@GetMapping
	public ResponseEntity<Response<EstadoObservacionRevisorDTO>> list(@RequestParam EstadoObservacionRevisorDTO dto) {
		
		
		List<EstadoObservacionRevisorDTO> lista = new ArrayList<>();
		lista.add(EstadoObservacionRevisorDTO.create()); 
		lista.add(EstadoObservacionRevisorDTO.create()); 
		lista.add(EstadoObservacionRevisorDTO.create()); 
		lista.add(EstadoObservacionRevisorDTO.create()); 
		lista.add(EstadoObservacionRevisorDTO.create()); 
		
		List<String> messages = new ArrayList<>();
		messages.add("Estados de tipo relacion institucion consultados  exitosamente");
 		
		Response<EstadoObservacionRevisorDTO> response = new Response<>(lista, messages);
		
		return new ResponseEntity<Response<EstadoObservacionRevisorDTO>>
		(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public EstadoObservacionRevisorDTO listById(@PathVariable UUID id){
		return EstadoObservacionRevisorDTO.create().setIdentificador(id);
	}
	
	@PostMapping
	public ResponseEntity<Response<EstadoObservacionRevisorDTO>> create(@RequestParam EstadoObservacionRevisorDTO dto) {
		
		var statusCode = HttpStatus.OK;
		var response = new Response<EstadoObservacionRevisorDTO>();
		
		try {
			var result = RegistrarEstadoObservacionRevisorValidation.validate(dto);
			
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
	public EstadoObservacionRevisorDTO update(@PathVariable UUID id, @RequestParam EstadoObservacionRevisorDTO dto) {
		return dto.setIdentificador(id);
	}
	
	@DeleteMapping("/{id}")
	public EstadoObservacionRevisorDTO delete(@PathVariable UUID id) {
		return EstadoObservacionRevisorDTO.create().setIdentificador(id);
	}
}