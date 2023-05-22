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
import co.edu.uco.business.facade.EstadoComentarioLectorFacade;
import co.edu.uco.business.facade.EstadoTipoRelacionInstitucionFacade;
import co.edu.uco.business.facade.impl.EstadoCategoriaFacadeImpl;
import co.edu.uco.business.facade.impl.EstadoComentarioLectorFacadeImpl;
import co.edu.uco.business.facade.impl.EstadoTipoRelacionFacadeImpl;
import co.edu.uco.publiuco.api.controller.response.Response;
import co.edu.uco.publiuco.api.validator.estadocategoria.RegistrarEstadoCategoriaValidation;
import co.edu.uco.publiuco.api.validator.estadocomentariolector.RegistrarEstadoComentarioLectorValidation;
import co.edu.uco.publiuco.api.validator.estadotiporelacioninstitucion.RegistrarEstadoTipoRelacionInstitucionValidation;
import co.edu.uco.publiuco.crosscutting.exception.PubliucoException;
import co.edu.uco.publiuco.dto.EstadoCategoriaDTO;
import co.edu.uco.publiuco.dto.EstadoComentarioLectorDTO;
import co.edu.uco.publiuco.dto.EstadoTipoRelacionInstitucionDTO;

@RestController
@RequestMapping("publiuco/api/v1/estadotiporelacioninstitucion")
public final class EstadoComentarioLectorController {
	
	
	private EstadoComentarioLectorFacade facade;
	
	public EstadoComentarioLectorController() {
		facade = new EstadoComentarioLectorFacadeImpl();
	}

	@GetMapping("/dummy")
	public EstadoComentarioLectorDTO dummy() {
		return EstadoComentarioLectorDTO.create();
	}
	
	@GetMapping
	public ResponseEntity<Response<EstadoComentarioLectorDTO>> list(@RequestParam EstadoComentarioLectorDTO dto) {
		
		
		List<EstadoComentarioLectorDTO> lista = new ArrayList<>();
		lista.add(EstadoComentarioLectorDTO.create()); 
		lista.add(EstadoComentarioLectorDTO.create()); 
		lista.add(EstadoComentarioLectorDTO.create()); 
		lista.add(EstadoComentarioLectorDTO.create()); 
		lista.add(EstadoComentarioLectorDTO.create()); 
		
		List<String> messages = new ArrayList<>();
		messages.add("Estados de tipo relacion institucion consultados  exitosamente");
 		
		Response<EstadoComentarioLectorDTO> response = new Response<>(lista, messages);
		
		return new ResponseEntity<Response<EstadoComentarioLectorDTO>>
		(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public EstadoComentarioLectorDTO listById(@PathVariable UUID id){
		return EstadoComentarioLectorDTO.create().setIdentificador(id);
	}
	
	@PostMapping
	public ResponseEntity<Response<EstadoComentarioLectorDTO>> create(@RequestParam EstadoComentarioLectorDTO dto) {
		
		var statusCode = HttpStatus.OK;
		var response = new Response<EstadoComentarioLectorDTO>();
		
		try {
			var result = RegistrarEstadoComentarioLectorValidation.validate(dto);
			
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
	public EstadoComentarioLectorDTO update(@PathVariable UUID id, @RequestParam EstadoComentarioLectorDTO dto) {
		return dto.setIdentificador(id);
	}
	
	@DeleteMapping("/{id}")
	public EstadoComentarioLectorDTO delete(@PathVariable UUID id) {
		return EstadoComentarioLectorDTO.create().setIdentificador(id);
	}
}