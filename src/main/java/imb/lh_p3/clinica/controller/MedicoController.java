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
	
			@Autowired // Inyección de dependencias del servicio de Medicos
			IMedicoService service;
	
			// Méttodo para obtener una lista de todos los Medicos
			@GetMapping("/Medicos")
			public ResponseEntity<DTOResponse<List<Medico>>> mostrarTodosLosMedicos(){
	
				// Llamada al servicio para obtener todos los Medicos
				List<Medico> lista = service.mostrarTodos();
				// Se crea un objeto DTO para estructurar la respuesta
				DTOResponse<List<Medico>> dto = new DTOResponse<>(200,"Lista de Medicos",lista);
				// Devuelve la respuesta con un código HTTP 200 (OK) y la lista de Medicos
				return ResponseEntity.status(HttpStatus.OK).body(dto);
	
				/*
				public DTOResponse<List<Medico>> mostrarTodosLosMedicos(){
				DTOResponse<List<Medico>> dto = new DTOResponse<>();
				List<Medico> lista = service.mostrarTodos();
	
				dto.setStatus(200);
				dto.setMessage(null);
				dto.setData(lista);
				return dto;*/
	
			}
	
			// Méttodo para obtener un Medico específico por su ID
			@GetMapping("/Medicos/{id}")
			public ResponseEntity<DTOResponse<Medico>> mostrarMedicosPorId(@PathVariable("id") Long id) {
	
				// Si el Medico existe, se devuelve el objeto en un DTO y un estatus 200 (OK)
				if ( service.mostrarPorId(id) != null) {
					DTOResponse<Medico> dto = new DTOResponse<>(200, "200 OK",  service.mostrarPorId(id));
					return ResponseEntity.status(HttpStatus.OK).body(dto);
				} else {
					// Si el Medico no existe, se lanza una excepción personalizada, que será manejada por GlobalExceptionHandler
					throw new ElementoNoExisteException("El Medico con id "+ id+" no existe");
				}
	
				/*if (service.existe(id)) {
					DTOResponse<Medico> dto = new DTOResponse<>(200, "", service.mostrarPorId(id));
					return ResponseEntity.status(HttpStatus.OK).body(dto);
				}else{
					DTOResponse<Medico> dto = new DTOResponse<>(404, "El Medico buscado no existe", null);
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
				}*/
	
			}
	
			/*
			public DTOResponse<Medico> mostrarMedicosPorId(@PathVariable("id") Long id){
				DTOResponse<Medico> dto = new DTOResponse<>();
				if (service.existe(id)){
					dto.setStatus(200);
					dto.setData(service.mostrarPorId(id));
				}else{
					dto.setStatus(404);
					List<String> errores = new ArrayList<>();
					errores.add("Este Medico no existe");
					dto.setMessage(errores);
				}
				return dto;
			}*/
	
			// Méttodo para crear un nuevo Medico
			@PostMapping("/Medicos")
			public ResponseEntity<DTOResponse<Medico>> crearRegistro(@Valid @RequestBody Medico Medico){
			//La notación @Valid activará la validación de los datos recibidos en las solicitudes. Si los datos no cumplen con las restricciones, se lanzará una ConstraintViolationException o MethodArgumentNotValidException
	
				// Verifica si el Medico ya existe usando su ID
				if(service.existe(Medico.getId())){
					/*DTOResponse<Medico> dto = new DTOResponse<>(500,"El identificador de este Medico" + Medico.getId().toString() + " ya existe",null);
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);*/
	
					// Si el Medico ya existe, se lanza una excepción
				throw new ElementeYaExisteException("El Medico ya existe");
				}else {
					// Si no existe, se crea un objeto DTO para guardar el Medico. Se usa estatus 201 (CREATED)
					DTOResponse<Medico> dto = new DTOResponse<Medico>(201,"Medico agregado correctamente",service.guardar(Medico));
					return ResponseEntity.status(HttpStatus.CREATED).body(dto);
				}
			}
	
			// Méttodo para actualizar un Medico existente
			@PutMapping("/Medicos")
			public ResponseEntity<DTOResponse<Medico>> actualizarRegistro(@Valid @RequestBody Medico Medico){
				// Verifica si el Medico existe usando su ID.
				if(service.existe(Medico.getId())) {
	
					//Si existe, se actualiza el Medico con dto y un estatus 200 (OK)
					DTOResponse<Medico> dto = new DTOResponse<>(200, "", service.guardar(Medico));
					return ResponseEntity.status(HttpStatus.OK).body(dto);
				}else{
					/*DTOResponse<Medico> dto = new DTOResponse<>(404, "El id "+Medico.getId().toString()+ " no existe", null);
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);*/
	
					throw new ElementoNoExisteException("El Medico a actualizar no existe");
					// Si no existe, se lanza una excepción indicando que no se encontró el Medico
				}
			}
	
			// Método para eliminar un Medico por su ID
			@DeleteMapping("/Medicos/{id}")
			public ResponseEntity<DTOResponse<?>> eliminarRegistro(@PathVariable("id") Long id){
				if (service.existe(id)){
	
					//Verifica si el Medico existe y se elimina el Medico
					service.eliminar(id);
					DTOResponse<?> dtoSi = new DTOResponse<>(200, "Medico eliminado correctamente", null);
					return ResponseEntity.status(HttpStatus.OK).body(dtoSi);
				}else {
					/*DTOResponse<?> dtoNo = new DTOResponse<>(400, "El Medico a aliminar no existe", null);
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dtoNo);*/
	
					// Si no existe, se lanza una excepción indicando que no se encontró
					throw new ElementoNoExisteException("El Medico a aliminar no existe");
				}
			}
	
	
			//Validaciones de entrada de datos enviada a GlobalExceptionHandler
			/*@ExceptionHandler(ConstraintViolationException.class)
			DTOResponse<Medico> controladorExcepciones (ConstraintViolationException e){
				List<String> errors = new ArrayList<>();
				for (ConstraintViolation<?> violation : e.getConstraintViolations()){
					errors.add(violation.getMessage());
				}
				DTOResponse<Medico> response = new DTOResponse<>(404,errors,null);
				return response;
	
			}*/
		}
