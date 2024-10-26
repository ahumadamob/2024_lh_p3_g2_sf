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

import imb.lh_p3.Estructura.entity.Especialidad;
import imb.lh_p3.Estructura.service.IEspecialidadService;
import imb.lh_p3.Estructura.util.ApiResponse;

@RestController
public class EspecialidadController {

	@Autowired
	IEspecialidadService service;
	
	@GetMapping("/especialidad")
	ApiResponse<List<Especialidad>> mostrarTodasLasEspecialidades(){
		ApiResponse<List<Especialidad>> response = new ApiResponse<>();
		List<Especialidad> lista = service.mostrarTodos();
		
		if(lista.isEmpty()) {
			response.setError("No existe ningun especialidad");			
		}else {
			response.setData(lista);
		}
		
		return response;
	}
	
	@GetMapping("/especialidad/{id}")
	ApiResponse<Especialidad> mostrarEspecialidadPorId(@PathVariable("id") Long id){
		ApiResponse<Especialidad> response = new ApiResponse<>();
		Especialidad especialidad = service.mostrarPorId(id);
		
		if(especialidad == null) {
			response.setError("No existe el ID " + id.toString());
			
		}else {
			response.setData(especialidad);
		}		
		return response;
	}
	
	@PostMapping("/especialidad")
	ApiResponse<Especialidad> crearRegistro(@RequestBody Especialidad especialidad){
		ApiResponse<Especialidad> response = new ApiResponse<>();
		if(service.existe(especialidad.getId())) {
			response.setError("Ya existe este elemento");
		}else {
			Especialidad especialidadGuardada = service.guardar(especialidad);
			response.setData(especialidadGuardada);
		}
		return response;
	}	
	
	@PutMapping("/especialidad")
	ApiResponse<Especialidad> actualizarRegistro(@RequestBody Especialidad especialidad){
		ApiResponse<Especialidad> response = new ApiResponse<>();
		if(service.existe(especialidad.getId())) {
			Especialidad especialidadGuardada = service.guardar(especialidad);
			response.setData(especialidadGuardada);
		}else {
			response.setError("La especialidad no existe");
		}
		return response;
	}
	
	@DeleteMapping("/especialidad/{id}")
	String eliminarRegistro(@PathVariable("id") Long id){
		if(service.existe(id)) {
			service.eliminar(id);
			return "La especialidad se eliminó";
		}else {
			return "El id no existe";
		}
		
	}


}
