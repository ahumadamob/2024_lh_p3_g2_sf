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

import imb.lh_p3.estructura.entity.Medico;
import imb.lh_p3.estructura.service.IMedicoService;
import imb.lh_p3.estructura.util.DTOResponse;

@RestController
public class MedicoController {

	@Autowired
	IMedicoService service;
	
	@GetMapping("/medicos")
	DTOResponse<List<Medico>> mostrarTodos(){
		
		DTOResponse<List<Medico>> response = new DTOResponse<>();
		List<Medico> lista = service.mostrarTodos();
		
		if(lista.isEmpty()) {
			response.setError("No hay Médicos guardados");
		}else {
			response.setData(lista);
		}
		
		return response;
		
	}
	
	@GetMapping("/medicos/{id}")
	DTOResponse<Medico> mostrarPorId(@PathVariable("id") Long id){
		DTOResponse<Medico> response = new DTOResponse<>();
		Medico medico = service.mostrarPorId(id);
		
		if(medico == null) {
			response.setError("No exite el ID: "+ id.toString());
		}else {
			response.setData(medico);
		}
		return response;
		
	}
	
	@PostMapping("/medicos")
	DTOResponse<Medico> crearRegistro(@RequestBody Medico medico){
		DTOResponse<Medico> response = new DTOResponse<>();
		if(service.existe(medico.getId())) {
			response.setError("Ya existe este registro");
		}else {
			Medico medicoGuardado = service.guardar(medico);
			response.setData(medicoGuardado);
		}
		return response;
	}
	
	@PutMapping("/medicos")
	DTOResponse<Medico> actualizarRegistro(@RequestBody Medico medico){
		DTOResponse<Medico> response = new DTOResponse<>();
		if(service.existe(medico.getId())) {
			Medico medicoGuardado = service.guardar(medico);
			response.setData(medicoGuardado);
		}else {
			response.setError("El registro no existe");
		}
		return response;
	}
	
	@DeleteMapping("/medicos/{id}")
	String eliminarRegistro(@PathVariable("id") Long id){
		if(service.existe(id)) {
			service.eliminar(id);
			return "El registro se eliminó correctamente";
		}else {
			return "El id no existe";
		}
		
	}

}
*/