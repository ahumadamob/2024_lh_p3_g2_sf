package imb.lh_p3.clinica.controller;

import java.util.List;

import imb.lh_p3.clinica.exceptions.ElementeYaExisteException;
import imb.lh_p3.clinica.exceptions.ElementoNoExisteException;
import imb.lh_p3.clinica.util.DTOResponse;
import jakarta.validation.Valid;
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

import imb.lh_p3.clinica.entity.Paciente;
import imb.lh_p3.clinica.service.PacienteService;

@RestController
public class PacienteController {

	@Autowired
	PacienteService service;

	@GetMapping("/paciente")
	public ResponseEntity<DTOResponse<List<Paciente>>> mostrarTodosLosPacientes(){
		List<Paciente> lista = service.mostrarTodos();
		if(lista.isEmpty()){
			throw new ElementoNoExisteException("No hay pacientes registrados");
		}else{
			DTOResponse<List<Paciente>> dto = new DTOResponse<>(200, "Lista de Pacientes", lista);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
	}

	@GetMapping("/paciente/{id}")
	public ResponseEntity<DTOResponse<Paciente>> mostrarPacientePorId(@PathVariable("id") Long id){
		Paciente paciente = service.mostrarPorId(id);
		if (paciente == null){
			throw new ElementoNoExisteException("El paciente con el id: "+ id + " no existe");
		}else{
			DTOResponse<Paciente> dto = new DTOResponse<>(200,"Paciente: ",paciente);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
	}

	@PostMapping("/paciente")
	public ResponseEntity<DTOResponse<Paciente>> guardarPaciente(@Valid @RequestBody Paciente paciente){
		if(service.existe(paciente.getIdPaciente())){
			throw new ElementeYaExisteException("Ya hay un registro guardado con este numero de DNI");
		}else{
			DTOResponse<Paciente> dto = new DTOResponse<>(201, "Paciente guardado correctamente", service.guardar(paciente));
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
		}
	}

	@PutMapping("/paciente")
	public ResponseEntity<DTOResponse<Paciente>> actualizarPaciente(@Valid @RequestBody Paciente paciente){
		if (service.existe(paciente.getIdPaciente())){
			DTOResponse<Paciente> dto = new DTOResponse<>(200,"Paciente actualizado correctamente", service.guardar(paciente));
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}else{
			throw new ElementoNoExisteException("El paciente que esta intentando actualizar no existe");
		}
	}

	@DeleteMapping("/paciente/{id}")
	public ResponseEntity<DTOResponse<?>> eliminarPaciente(@PathVariable("id") Long id){
		if (service.existe(id)){
			service.eliminar(id);
			DTOResponse<?> dtoSi = new DTOResponse<>(200, "Paciente eliminado correctamente", null);
			return ResponseEntity.status(HttpStatus.OK).body(dtoSi);
		}else {
			throw new ElementoNoExisteException("El paciente a elimiinar no existe");
		}
	}
	}
