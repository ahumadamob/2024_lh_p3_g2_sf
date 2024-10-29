package imb.lh_p3.clinica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import imb.lh_p3.clinica.entity.Paciente;
import imb.lh_p3.clinica.service.PacienteService;
import imb.lh_p3.clinica.util.ApiResponse;

@RestController
public class PacienteController {

	@Autowired
	PacienteService service;

	@GetMapping("/paciente")
	ApiResponse<List<Paciente>> mostrarTodosLosPacientes(){
		ApiResponse<List<Paciente>> response = new ApiResponse<>();
		List<Paciente> lista = service.mostrarTodos();

		if (lista.isEmpty()) {
			response.setError("No existe ningún paciente");
		} else {
			response.setData(lista);
		}
		return response;
	}

	@GetMapping ("/paciente/{id}")
	ApiResponse<Paciente> mostrarPacientePorId(@PathVariable("id")Long id){
		ApiResponse <Paciente> response = new ApiResponse <>();
		Paciente paciente = service.mostrarPorId(id);

		if (paciente==null) {
			response.setError("No existe el ID "+ id.toString());
		} else {
			response.setData(paciente);
		}

		return response;
	}

	@PostMapping("/paciente")
	ApiResponse<Paciente> crearPaciente (@RequestBody Paciente paciente){
		ApiResponse <Paciente> response = new ApiResponse <>();
		if (service.existe(paciente.getId())) {
			response.setError("El paciente ya existe");
		}else {
			Paciente pacienteGuardado = service.guardar(paciente);
			response.setData(pacienteGuardado);
		}
		return response;
	}

	@PutMapping("/paciente")
	ApiResponse<Paciente> actualizarPaciente (@RequestBody Paciente paciente){
		ApiResponse <Paciente> response = new ApiResponse <>();
		if (service.existe(paciente.getId())) {
			Paciente pacienteGuardado = service.guardar(paciente);
			response.setData(pacienteGuardado);
		}else {
			response.setError("El paciente no existe");

		}
		return response;
	}

	@DeleteMapping("/paciente/{id}")
	String eliminarPaciente(@PathVariable("id") Long id) {
		if(service.existe(id)) {
			service.eliminar(id);
			return "Paciente eliminado con éxito";
		}else {
			return "El id  no existe";
		}
	}
	}
