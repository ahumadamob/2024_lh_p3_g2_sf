package imb.lh_p3.Estructura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import imb.lh_p3.Estructura.entity.Turno;
import imb.lh_p3.Estructura.service.ITurnoService;
import imb.lh_p3.Estructura.util.ApiResponse;




@RestController
public class TurnoController {
	
	@Autowired
	ITurnoService service;
	
	@GetMapping("/turnos")
	ApiResponse<List<Turno>> mostrarTodosLosTurnos(){
		ApiResponse<List<Turno>> response = new ApiResponse<>();
		List<Turno> lista = service.mostrarTodos();
		
		if(lista.isEmpty()) {
			response.setError("No se encuentra un registro existe para el turno");			
		}else {
			response.setData(lista);
		}
		
		return response;
	}
	
	@GetMapping("/turnos/{id}")
	ApiResponse<Turno> mostrarTurnosPorId(@PathVariable("id") Long id){
		ApiResponse<Turno> response = new ApiResponse<>();
		Turno turno = service.mostrarPorId(id);
		
		if(turno == null) {
			response.setError("No existe el ID " + id.toString());
			
		}else {
			response.setData(turno);
		}		
		return response;
	}
	
	@PostMapping("/turnos")
	ApiResponse<Turno> crearRegistro(@RequestBody Turno turno){
		ApiResponse<Turno> response = new ApiResponse<>();
		if(service.existe(turno.getId())) {
			response.setError("Ya existe este registro de turno");
		}else {
			Turno turnoGuardado = service.guardar(turno);
			response.setData(turnoGuardado);
		}
		return response;
	}	
	
	@PutMapping("/turnos")
	ApiResponse<Turno> actualizarRegistro(@RequestBody Turno turno){
		ApiResponse<Turno> response = new ApiResponse<>();
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

}
