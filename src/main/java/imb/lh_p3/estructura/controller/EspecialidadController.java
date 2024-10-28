package imb.lh_p3.estructura.controller;


import java.util.List;


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


import imb.lh_p3.estructura.entity.Especialidad;
import imb.lh_p3.estructura.service.IEspecialidadService;
import imb.lh_p3.estructura.util.DTOResponse;
import jakarta.validation.ValidationException;

@RestController
public class EspecialidadController {

	@Autowired
	IEspecialidadService service;
	
	@GetMapping("/especialidad")
	public ResponseEntity<DTOResponse<List<Especialidad>>> mostrarTodos() {
	    DTOResponse<List<Especialidad>> response = new DTOResponse<>();
	    List<Especialidad> lista = service.mostrarTodos();

	    if (lista.isEmpty()) {
	    response.setStatus(400);
		response.setMessage(null);
		response.setData(lista);
		return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	} else {
		    response.setStatus(200);
			response.setMessage(null);
			response.setData(lista);
			return  ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	}

	@GetMapping("/especialidad/{id}")
	public ResponseEntity<DTOResponse<Especialidad>> mostrarEspecialidadPorId(@PathVariable("id") Long id) {
	    DTOResponse<Especialidad> response = new DTOResponse<>();

	    try {
	        Especialidad especialidad = service.mostrarPorId(id);

	        if (especialidad != null) {
	            response = new DTOResponse<>(200, "Las especialidades son:", especialidad);
	            return ResponseEntity.status(HttpStatus.OK).body(response);
	        } else {
	            response = new DTOResponse<>(404, "La especialidad con id " + id + " no existe", null);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }
	    } catch (ValidationException e) {
	        response = new DTOResponse<>(400, "Error de validación: " + e.getMessage(), null);
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    } catch (Exception e) {
	        response = new DTOResponse<>(500, "Error interno del servidor: " + e.getMessage(), null);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}

			
		
		
	@PostMapping("/especialidad")
	public ResponseEntity<DTOResponse<Especialidad>> crearRegistro(@RequestBody Especialidad especialidad){
		DTOResponse<Especialidad> response = new DTOResponse<>();
		
		
		if(service.existe(especialidad.getId())) {
			
			DTOResponse<Especialidad> dto = new DTOResponse<Especialidad>(404,"El id " + especialidad.getId().toString() + " SI existe",null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
		}else {		
		
			DTOResponse<Especialidad> dto = new DTOResponse<Especialidad>(201,"Registro creado correctamente",service.guardar(especialidad));			
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
		}		
}	

		
	@PutMapping("/especialidad")
	public ResponseEntity<DTOResponse<Especialidad>> actualizarRegistro(@RequestBody Especialidad especialidad){
		DTOResponse<Especialidad> response = new DTOResponse<>();
		
		
		if(service.existe(especialidad.getId())) {
			DTOResponse<Especialidad> dto = new DTOResponse<Especialidad>(200,"Se modifico correctamente",service.guardar(especialidad));
			return ResponseEntity.status(HttpStatus.OK).body(dto);
			
		}else {
			DTOResponse<Especialidad> dto = new DTOResponse<Especialidad>(404,"El id " + especialidad.getId().toString() + " SI existe",null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
		}		
}	

	
	
	
	@DeleteMapping("/especialidad/{id}")
	
	public ResponseEntity<DTOResponse<?>> eliminarEspecialidad(@PathVariable("id") Long id) {
		if(service.existe(id)) {
			service.eliminar(id);
			DTOResponse<?> dtoSi = new DTOResponse<>(200, " se eliminó correctamente", null);
			return ResponseEntity.status(HttpStatus.OK).body(dtoSi);
		}else {
			DTOResponse<?> dtoNo = new DTOResponse<>(404, "La especialidad no existe", null);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dtoNo);
		}
	}


}
