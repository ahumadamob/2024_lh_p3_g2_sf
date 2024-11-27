package imb.lh_p3.clinica.controller;

import java.util.List;

import imb.lh_p3.clinica.exceptions.ElementeYaExisteException;
import imb.lh_p3.clinica.exceptions.ElementoNoExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import imb.lh_p3.clinica.entity.Consulta;
import imb.lh_p3.clinica.service.IConsultaService;
import imb.lh_p3.clinica.util.DTOResponse;
import jakarta.validation.Valid;

@RestController
public class ConsultaController {

	@Autowired
	IConsultaService service;


	@GetMapping("/consulta")
	public ResponseEntity<DTOResponse<List<Consulta>>> mostrarTodasLasConsultas(){
		List<Consulta> lista = service.mostrarTodos();
		if(lista.isEmpty()){
			throw new ElementoNoExisteException("No hay consultas registradas");
		}else{
			DTOResponse<List<Consulta>> dto = new DTOResponse<>(200, "Lista de consultas", lista);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
	}

	@GetMapping("/consulta/{id}")
	public ResponseEntity<DTOResponse<Consulta>> mostrarConsultaPorId(@PathVariable("id") Long id){
		Consulta consulta = service.mostrarPorId(id);
		if(consulta == null){
			throw new ElementoNoExisteException("La consulta con el id "+ id + " no existe");
		}else{
			DTOResponse<Consulta> dto = new DTOResponse<>(200, "Consulta:", consulta);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
	}

	@PostMapping("/consulta")
	public ResponseEntity<DTOResponse<Consulta>> guardarConsulta(@Valid @RequestBody Consulta consulta){
		if(service.existe(consulta.getIdConsulta())){
			throw new ElementeYaExisteException("Ya hay guardada una consulta ");
		}else{
			DTOResponse<Consulta> dto = new DTOResponse<>(201, "Consulta guardada correctamente", service.guardar(consulta));
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
		}
	}

	@PutMapping("/consulta")
	public ResponseEntity<DTOResponse<Consulta>> actualizarConsulta(@Valid @RequestBody Consulta consulta){
		if(service.existe(consulta.getIdConsulta())){
			DTOResponse<Consulta> dto = new DTOResponse<>(200, "Consulta actualizada correctamente", service.guardar(consulta));
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}else{
			throw new ElementoNoExisteException("La consulta que esta tratando de actualizar no existe");
		}
	}

	@DeleteMapping("/consulta/{id}")
	public ResponseEntity<DTOResponse<?>> eliminarConsulta(@PathVariable("id")Long id){
		if(service.existe(id)){
			service.eliminar(id);
			DTOResponse<?> dto = new DTOResponse<>(200, "Consulta eliminada correctamente", null);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}else{
			throw new ElementoNoExisteException("La consulta a eliminar no existe");
		}
	}


}
