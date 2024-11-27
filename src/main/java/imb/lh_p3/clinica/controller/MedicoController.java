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

import imb.lh_p3.clinica.entity.Medico;
import imb.lh_p3.clinica.exceptions.ElementeYaExisteException;
import imb.lh_p3.clinica.exceptions.ElementoNoExisteException;
import imb.lh_p3.clinica.service.IMedicoService;
import imb.lh_p3.clinica.util.DTOResponse;
import jakarta.validation.Valid;
	
		@RestController
		public class MedicoController {

			@Autowired
			IMedicoService service;

			@GetMapping("/medico")
			public ResponseEntity<DTOResponse<List<Medico>>> mostrarTodosLosMedicos(){
				List<Medico> lista = service.mostrarTodos();
				if(lista.isEmpty()){
					throw new ElementoNoExisteException("No hay medicos registrados");
				}else{
					DTOResponse<List<Medico>> dto = new DTOResponse<>(200, "Lista de medicos", lista);
					return ResponseEntity.status(HttpStatus.OK).body(dto);
				}
			}

			@GetMapping("/medico/{id}")
			public ResponseEntity<DTOResponse<Medico>> mostrarMedicoPorId(@PathVariable("id") Long id){
				Medico medico = service.mostrarPorId(id);
				if (medico == null){
					throw new ElementoNoExisteException("El medico con el id: "+ id + " no existe");
				}else{
					DTOResponse<Medico> dto = new DTOResponse<>(200,"Medico: ",medico);
					return ResponseEntity.status(HttpStatus.OK).body(dto);
				}
			}

			@PostMapping("/medico")
			public ResponseEntity<DTOResponse<Medico>> guardarMedico(@Valid @RequestBody Medico medico){
				if(service.existe(medico.getIdMedico())){
					throw new ElementeYaExisteException("Ya hay un registro guardado con este medico");
				}else{
					DTOResponse<Medico> dto = new DTOResponse<>(201, "Medico guardado correctamente", service.guardar(medico));
					return ResponseEntity.status(HttpStatus.CREATED).body(dto);
				}
			}

			@PutMapping("/medico")
			public ResponseEntity<DTOResponse<Medico>> actualizarMedico(@Valid @RequestBody Medico medico){
				if (service.existe(medico.getIdMedico())){
					DTOResponse<Medico> dto = new DTOResponse<>(200,"Médico actualizado correctamente", service.guardar(medico));
					return ResponseEntity.status(HttpStatus.OK).body(dto);
				}else{
					throw new ElementoNoExisteException("El médico que esta intentando actualizar no existe");
				}
			}

			@DeleteMapping("/medico/{id}")
			public ResponseEntity<DTOResponse<?>> eliminarMedico(@PathVariable("id") Long id){
				if (service.existe(id)){
					service.eliminar(id);
					DTOResponse<?> dtoSi = new DTOResponse<>(200, "Médico eliminado correctamente", null);
					return ResponseEntity.status(HttpStatus.OK).body(dtoSi);
				}else {
					throw new ElementoNoExisteException("El médico a elimiinar no existe");
				}
			}
		}