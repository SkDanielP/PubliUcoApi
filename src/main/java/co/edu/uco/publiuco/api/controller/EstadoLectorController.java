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
import co.edu.uco.business.facade.EstadoLectorFacade;
import co.edu.uco.business.facade.EstadoTipoRelacionInstitucionFacade;
import co.edu.uco.business.facade.impl.EstadoEscritorFacadeImpl;
import co.edu.uco.business.facade.impl.EstadoLectorFacadeImpl;
import co.edu.uco.business.facade.impl.EstadoTipoRelacionFacadeImpl;
import co.edu.uco.publiuco.api.controller.response.Response;
import co.edu.uco.publiuco.api.validator.estadoescritor.RegistrarEstadoEscritorValidation;
import co.edu.uco.publiuco.api.validator.estadolector.RegistrarEstadoLectorValidation;
import co.edu.uco.publiuco.api.validator.estadotiporelacioninstitucion.RegistrarEstadoTipoRelacionInstitucionValidation;
import co.edu.uco.publiuco.crosscutting.exception.PubliucoException;
import co.edu.uco.publiuco.dto.EstadoEscritorDTO;
import co.edu.uco.publiuco.dto.EstadoLectorDTO;
import co.edu.uco.publiuco.dto.EstadoTipoRelacionInstitucionDTO;

@RestController
@RequestMapping("publiuco/api/v1/estadotiporelacioninstitucion")
public final class EstadoLectorController {
	
	
	private EstadoLectorFacade facade;
	
	public EstadoLectorController() {
		facade = new EstadoLectorFacadeImpl();
	}

	@GetMapping("/dummy")
	public EstadoLectorDTO dummy() {
		return EstadoLectorDTO.create();
	}
	
	@GetMapping
	public ResponseEntity<Response<EstadoLectorDTO>> list(@RequestParam EstadoLectorDTO dto) {
		
		
		List<EstadoLectorDTO> lista = new ArrayList<>();
		lista.add(EstadoLectorDTO.create()); 
		lista.add(EstadoLectorDTO.create()); 
		lista.add(EstadoLectorDTO.create()); 
		lista.add(EstadoLectorDTO.create()); 
		lista.add(EstadoLectorDTO.create()); 
		
		List<String> messages = new ArrayList<>();
		messages.add("Estados de tipo relacion institucion consultados  exitosamente");
 		
		Response<EstadoLectorDTO> response = new Response<>(lista, messages);
		
		return new ResponseEntity<Response<EstadoLectorDTO>>
		(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public EstadoLectorDTO listById(@PathVariable UUID id){
		return EstadoLectorDTO.create().setIdentificador(id);
	}
	
	@PostMapping
	public ResponseEntity<Response<EstadoLectorDTO>> create(@RequestParam EstadoLectorDTO dto) {
		
		var statusCode = HttpStatus.OK;
		var response = new Response<EstadoLectorDTO>();
		
		try {
			var result = RegistrarEstadoLectorValidation.validate(dto);
			
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
	public EstadoLectorDTO update(@PathVariable UUID id, @RequestParam EstadoLectorDTO dto) {
		return dto.setIdentificador(id);
	}
	
	@DeleteMapping("/{id}")
	public EstadoLectorDTO delete(@PathVariable UUID id) {
		return EstadoLectorDTO.create().setIdentificador(id);
	}
}