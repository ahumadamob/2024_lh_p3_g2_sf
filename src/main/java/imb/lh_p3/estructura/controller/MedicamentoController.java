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

import imb.lh_p3.estructura.entity.Medicamento;
import imb.lh_p3.estructura.service.IMedicamentoService;
    import imb.lh_p3.estructura.util.DTOResponse;

	@RestController
	public class MedicamentoController {

		@Autowired
		IMedicamentoService service;
		
		@GetMapping("/medicamentos")
		DTOResponse<List<Medicamento>> mostrarTodosLosMedicamentos(){
			DTOResponse<List<Medicamento>> response = new DTOResponse<>();
			List<Medicamento> lista = service.mostrarTodos();
			
			if(lista.isEmpty()) {
				response.setError("No existe ningun medicamento");			
			}else {
				response.setData(lista);
			}
			
			return response;
		}
		
		@GetMapping("/medicamentos/{id}")
		DTOResponse<Medicamento> mostrarMedicamentosPorId(@PathVariable("id") Long id){
			DTOResponse<Medicamento> response = new DTOResponse<>();
			Medicamento medicamento = service.mostrarPorId(id);
			
			if(medicamento == null) {
				response.setError("No existe el ID " + id.toString());
				
			}else {
				response.setData(medicamento);
			}		
			return response;
		}
		
		@PostMapping("/medicamentos")
		DTOResponse<Medicamento> crearRegistro(@RequestBody Medicamento medicamento){
			DTOResponse<Medicamento> response = new DTOResponse<>();
			if(service.existe(medicamento.getId())) {
				response.setError("Ya existe este elemento");
			}else {
				Medicamento medicamentoGuardado = service.guardar(medicamento);
				response.setData(medicamentoGuardado);
			}
			return response;
		}	
		
		@PutMapping("/medicamentos")
		DTOResponse<Medicamento> actualizarRegistro(@RequestBody Medicamento medicamento){
			DTOResponse<Medicamento> response = new DTOResponse<>();
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
*/


