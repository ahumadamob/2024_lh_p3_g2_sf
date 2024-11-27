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

import imb.lh_p3.clinica.entity.Turno;
import imb.lh_p3.clinica.service.ITurnoService;


@RestController
public class TurnoController {

	@Autowired
	ITurnoService service;

	@GetMapping("/turno")
	public ResponseEntity<DTOResponse<List<Turno>>> mostrarTodosLosTurnos(){
		List<Turno> lista = service.mostrarTodos();
		if(lista.isEmpty()){
			throw new ElementoNoExisteException("No hay turnos registrados");
		}else{
			DTOResponse<List<Turno>> dto = new DTOResponse<>(200, "Lista de turnos", lista);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
	}

	@GetMapping("/turno/{id}")
	public ResponseEntity<DTOResponse<Turno>> mostrarTurnoPorId(@PathVariable("id") Long id){
		Turno turno = service.mostrarPorId(id);
		if (turno == null){
			throw new ElementoNoExisteException("El turno con el id: "+ id + " no existe");
		}else{
			DTOResponse<Turno> dto = new DTOResponse<>(200,"Turno: ",turno);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
	}
	//Verificar la comprobacion de cuando ya existe turno por id
	@PostMapping("/turno")
	public ResponseEntity<DTOResponse<Turno>> guardarTurno(@Valid @RequestBody Turno turno){
		if(service.existe(turno.getId())){
			throw new ElementeYaExisteException("Ya hay un turno guardado con este ID");
		}else{
			DTOResponse<Turno> dto = new DTOResponse<>(201, "Turno guardado correctamente", service.guardar(turno));
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
		}
	}

	@PutMapping("/turno")
	public ResponseEntity<DTOResponse<Turno>> actualizarTurno(@Valid @RequestBody Turno turno){
		if (service.existe(turno.getId())){
			DTOResponse<Turno> dto = new DTOResponse<>(200,"Turno actualizado correctamente", service.guardar(turno));
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}else{
			throw new ElementoNoExisteException("El turno que esta intentando actualizar no existe");
		}
	}

	@DeleteMapping("/turno/{id}")
	public ResponseEntity<DTOResponse<?>> eliminarTurno(@PathVariable("id") Long id){
		if (service.existe(id)){
			service.eliminar(id);
			DTOResponse<?> dtoSi = new DTOResponse<>(200, "Turno eliminado correctamente", null);
			return ResponseEntity.status(HttpStatus.OK).body(dtoSi);
		}else {
			throw new ElementoNoExisteException("El turno a elimiinar no existe");
		}
	}
}
