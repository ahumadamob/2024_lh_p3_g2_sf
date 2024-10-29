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

import imb.lh_p3.clinica.entity.Medicamento;
import imb.lh_p3.clinica.service.IMedicamentoService;
import imb.lh_p3.clinica.util.ApiResponse;

	@RestController
	public class MedicamentoController {

		@Autowired
		IMedicamentoService service;

		@GetMapping("/medicamentos")
		ApiResponse<List<Medicamento>> mostrarTodosLosMedicamentos(){
			ApiResponse<List<Medicamento>> response = new ApiResponse<>();
			List<Medicamento> lista = service.mostrarTodos();

			if(lista.isEmpty()) {
				response.setError("No existe ningun medicamento");
			}else {
				response.setData(lista);
			}

			return response;
		}

		@GetMapping("/medicamentos/{id}")
		ApiResponse<Medicamento> mostrarMedicamentosPorId(@PathVariable("id") Long id){
			ApiResponse<Medicamento> response = new ApiResponse<>();
			Medicamento medicamento = service.mostrarPorId(id);

			if(medicamento == null) {
				response.setError("No existe el ID " + id.toString());

			}else {
				response.setData(medicamento);
			}
			return response;
		}

		@PostMapping("/medicamentos")
		ApiResponse<Medicamento> crearRegistro(@RequestBody Medicamento medicamento){
			ApiResponse<Medicamento> response = new ApiResponse<>();
			if(service.existe(medicamento.getId())) {
				response.setError("Ya existe este elemento");
			}else {
				Medicamento medicamentoGuardado = service.guardar(medicamento);
				response.setData(medicamentoGuardado);
			}
			return response;
		}

		@PutMapping("/medicamentos")
		ApiResponse<Medicamento> actualizarRegistro(@RequestBody Medicamento medicamento){
			ApiResponse<Medicamento> response = new ApiResponse<>();
			if(service.existe(medicamento.getId())) {
				Medicamento medicamentoGuardado = service.guardar(medicamento);
				response.setData(medicamentoGuardado);
			}else {
				response.setError("El medicamento no existe");
			}
			return response;
		}

		@DeleteMapping("/medicamentos/{id}")
		String eliminarRegistro(@PathVariable("id") Long id){
			if(service.existe(id)) {
				service.eliminar(id);
				return "El medicamento se eliminó";
			}else {
				return "El id no existe";
			}

		}

	}



