package imb.lh_p3.clinica.controller;


import java.util.List;


import imb.lh_p3.clinica.exceptions.ElementoNoExisteException;
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

//import MGException.VerificaExistencia;
//import MGException.VerificaLaNoExistencia;
import imb.lh_p3.clinica.entity.Especialidad;
import imb.lh_p3.clinica.service.IEspecialidadService;
import imb.lh_p3.clinica.util.DTOResponse;
import jakarta.validation.ValidationException;

@RestController
public class EspecialidadController {

	@Autowired
	IEspecialidadService service;


	@GetMapping("/especialidad")
	public ResponseEntity<DTOResponse<List<Especialidad>>> mostrarTodasLasEspecialidades(){
		List<Especialidad> lista = service.mostrarTodos();
		if(lista.isEmpty()){
			throw new ElementoNoExisteException("No hay especialidades registradas");
		}else{
			DTOResponse<List<Especialidad>> dto = new DTOResponse<>(200, "Lista de Especialidades", lista);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
	}

	@GetMapping("/especialidad/{id}")
	public ResponseEntity<DTOResponse<Especialidad>> mostrarEspecialidadPorId(@PathVariable("id") Long id){
		Especialidad especialidad = service.mostrarPorId(id);
		if (especialidad == null){
			throw new ElementoNoExisteException("La especialidad con el id: "+ id + " no existe");
		}else{
			DTOResponse<Especialidad> dto = new DTOResponse<>(200,"Especialidad: ",especialidad);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
	}

	@PostMapping("/especialidad")
	public ResponseEntity<DTOResponse<Especialidad>> guardarEspecialidad(@Valid @RequestBody Especialidad especialidad){
		if(service.existe(especialidad.getIdEspecialidad())){
			throw new ElementoNoExisteException("Ya hay un registro guardado con este nombre de Especialidad");
		}else{
			DTOResponse<Especialidad> dto = new DTOResponse<>(201, "Especialidad guardada correctamente", service.guardar(especialidad));
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
		}
	}

	@PutMapping("/especialidad")
	public ResponseEntity<DTOResponse<Especialidad>> actualizarEspecialidad(@Valid @RequestBody Especialidad especialidad){
		if (service.existe(especialidad.getIdEspecialidad())){
			DTOResponse<Especialidad> dto = new DTOResponse<>(200,"Especialidad actualizada correctamente", service.guardar(especialidad));
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}else{
			throw new ElementoNoExisteException("La Especialidad que esta intentando actualizar no existe");
		}
	}

	@DeleteMapping("/especialidad/{id}")
	public ResponseEntity<DTOResponse<?>> eliminarEspecialidad(@PathVariable("id") Long id){
		if (service.existe(id)){
			service.eliminar(id);
			DTOResponse<?> dtoSi = new DTOResponse<>(200, "Especialidad eliminada correctamente", null);
			return ResponseEntity.status(HttpStatus.OK).body(dtoSi);
		}else {
			throw new ElementoNoExisteException("La especialidad a elimiinar no existe");
		}
	}
}
