package co.edu.uco.publiuco.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.publiuco.dto.EstadoTipoRelacionInstitucionDTO;

@RestController
@RequestMapping("publiuco/api/v1/estadotiporelacioninstitucion")
public final class EstadoTipoRelacionInstitucionController {

	@GetMapping("/dummy")
	public EstadoTipoRelacionInstitucionDTO dummy() {
		return EstadoTipoRelacionInstitucionDTO.create();
	}
	
	@GetMapping
	public List<EstadoTipoRelacionInstitucionDTO> listar() {
		List<EstadoTipoRelacionInstitucionDTO> lista = new ArrayList<>();
		lista.add(EstadoTipoRelacionInstitucionDTO.create()); 
		lista.add(EstadoTipoRelacionInstitucionDTO.create()); 
		lista.add(EstadoTipoRelacionInstitucionDTO.create()); 
		lista.add(EstadoTipoRelacionInstitucionDTO.create()); 
		lista.add(EstadoTipoRelacionInstitucionDTO.create()); 
		
		return lista;
	}
	
	@GetMapping("/{id}")
	public EstadoTipoRelacionInstitucionDTO obtenerPorId(@PathVariable UUID id){
		return EstadoTipoRelacionInstitucionDTO.create().setIdentificador(id);
	}
	
	@PostMapping
	public EstadoTipoRelacionInstitucionDTO crear(@RequestBody EstadoTipoRelacionInstitucionDTO dto) {
		return dto;
	}
	
	@PutMapping("/{id}")
	public EstadoTipoRelacionInstitucionDTO actualizar(@PathVariable UUID id, @RequestBody EstadoTipoRelacionInstitucionDTO dto) {
		return dto.setIdentificador(id);
	}
	
	@DeleteMapping("/{id}")
	public EstadoTipoRelacionInstitucionDTO eliminar(@PathVariable UUID id) {
		return EstadoTipoRelacionInstitucionDTO.create().setIdentificador(id);
	}
}