package imb.lh_p3.clinica.controller;

    
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

import imb.lh_p3.clinica.entity.Medicamento;
import imb.lh_p3.clinica.exceptions.ElementeYaExisteException;
import imb.lh_p3.clinica.exceptions.ElementoNoExisteException;
import imb.lh_p3.clinica.service.IMedicamentoService;
import imb.lh_p3.clinica.util.DTOResponse;
import jakarta.validation.Valid;

	@RestController
	public class MedicamentoController {

		@Autowired // Inyección de dependencias del servicio de medicamentos
		IMedicamentoService service;
		@GetMapping("/medicamento")
		public ResponseEntity<DTOResponse<List<Medicamento>>> mostrarTodosLosMedicamentos(){
			List<Medicamento> lista = service.mostrarTodos();
			if(lista.isEmpty()){
				throw new ElementoNoExisteException("No hay medicamentos registrados");
			}else{
				DTOResponse<List<Medicamento>> dto = new DTOResponse<>(200, "Lista de medicamentos", lista);
				return ResponseEntity.status(HttpStatus.OK).body(dto);
			}
		}

		@GetMapping("/medicamento/{id}")
		public ResponseEntity<DTOResponse<Medicamento>> mostrarMedicamentoPorId(@PathVariable("id") Long id){
			Medicamento medicamento = service.mostrarPorId(id);
			if(medicamento == null){
				throw new ElementoNoExisteException("El medicamento con el id "+ id + " no existe");
			}else{
				DTOResponse<Medicamento> dto = new DTOResponse<>(200, "Medicamento:", medicamento);
				return ResponseEntity.status(HttpStatus.OK).body(dto);
			}
		}

		@PostMapping("/medicamento")
		public ResponseEntity<DTOResponse<Medicamento>> guardarMedicamento(@Valid @RequestBody Medicamento medicamento){
			if(service.existe(medicamento.getId())){
				throw new ElementeYaExisteException("Este medicamento ya esta guardado ");
			}else{
				DTOResponse<Medicamento> dto = new DTOResponse<>(201, "Medicamento guardado correctamente", service.guardar(medicamento));
				return ResponseEntity.status(HttpStatus.CREATED).body(dto);
			}
		}

		@PutMapping("/medicamento")
		public ResponseEntity<DTOResponse<Medicamento>> actualizarMedicamento(@Valid @RequestBody Medicamento medicamento){
			if(service.existe(medicamento.getId())){
				DTOResponse<Medicamento> dto = new DTOResponse<>(200, "Medicamento actualizada correctamente", service.guardar(medicamento));
				return ResponseEntity.status(HttpStatus.OK).body(dto);
			}else{
				throw new ElementoNoExisteException("El medicamento que esta tratando de actualizar no existe");
			}
		}

		@DeleteMapping("/medicamento/{id}")
		public ResponseEntity<DTOResponse<?>> eliminarMedicamento(@PathVariable("id")Long id){
			if(service.existe(id)){
				service.eliminar(id);
				DTOResponse<?> dto = new DTOResponse<>(200, "Medicamento eliminado correctamente", null);
				return ResponseEntity.status(HttpStatus.OK).body(dto);
			}else{
				throw new ElementoNoExisteException("El medicamento a eliminar no existe");
			}
		}
	}




