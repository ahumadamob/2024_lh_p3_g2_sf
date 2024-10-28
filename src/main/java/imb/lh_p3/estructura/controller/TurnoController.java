package imb.lh_p3.estructura.controller;

/*import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import imb.lh_p3.estructura.entity.Turno;
import imb.lh_p3.estructura.service.ITurnoService;
import imb.lh_p3.estructura.util.DTOResponse;




@RestController
public class TurnoController {
	
	@Autowired
	ITurnoService service;
	
	@GetMapping("/turnos")
	DTOResponse<List<Turno>> mostrarTodosLosTurnos(){
		DTOResponse<List<Turno>> response = new DTOResponse<>();
		List<Turno> lista = service.mostrarTodos();
		
		if(lista.isEmpty()) {
			response.setError("No se encuentra un registro existe para el turno");			
		}else {
			response.setData(lista);
		}
		
		return response;
	}
	
	@GetMapping("/turnos/{id}")
	DTOResponse<Turno> mostrarTurnosPorId(@PathVariable("id") Long id){
		DTOResponse<Turno> response = new DTOResponse<>();
		Turno turno = service.mostrarPorId(id);
		
		if(turno == null) {
			response.setError("No existe el ID " + id.toString());
			
		}else {
			response.setData(turno);
		}		
		return response;
	}
	
	@PostMapping("/turnos")
	DTOResponse<Turno> crearRegistro(@RequestBody Turno turno){
		DTOResponse<Turno> response = new DTOResponse<>();
		if(service.existe(turno.getId())) {
			response.setError("Ya existe este registro de turno");
		}else {
			Turno turnoGuardado = service.guardar(turno);
			response.setData(turnoGuardado);
		}
		return response;
	}	
	
	@PutMapping("/turnos")
	DTOResponse<Turno> actualizarRegistro(@RequestBody Turno turno){
		DTOResponse<Turno> response = new DTOResponse<>();
		if(service.existe(turno.getId())) {
			Turno turnoGuardado = service.guardar(turno);
			response.setData(turnoGuardado);
		}else {
			response.setError("El elemento no existe");
		}
		return response;
	}
	
	@DeleteMapping("/turnos/{id}")
	String eliminarRegistro(@PathVariable("id") Long id){
		if(service.existe(id)) {
			service.eliminar(id);
			return "El turno se elimino correctamente";
		}else {
			return "El id no es existe";
		}
		
	}

}*/
