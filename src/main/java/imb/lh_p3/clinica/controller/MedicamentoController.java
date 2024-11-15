package imb.lh_p3.clinica.controller;

    
	import java.util.ArrayList;
	import java.util.List;

	import jakarta.validation.ConstraintViolation;
	import jakarta.validation.ConstraintViolationException;
	import jakarta.validation.Valid;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;

import imb.lh_p3.clinica.entity.Medicamento;
import imb.lh_p3.clinica.exceptions.ElementeYaExisteException;
import imb.lh_p3.clinica.exceptions.ElementoNoExisteException;
import imb.lh_p3.clinica.service.IMedicamentoService;
import imb.lh_p3.clinica.util.DTOResponse;

	@RestController
	public class MedicamentoController {

		@Autowired // Inyección de dependencias del servicio de medicamentos
		IMedicamentoService service;

		// Méttodo para obtener una lista de todos los medicamentos
		@GetMapping("/medicamentos")
		public ResponseEntity<DTOResponse<List<Medicamento>>> mostrarTodosLosMedicamentos(){

			// Llamada al servicio para obtener todos los medicamentos
			List<Medicamento> lista = service.mostrarTodos();
			// Se crea un objeto DTO para estructurar la respuesta
			DTOResponse<List<Medicamento>> dto = new DTOResponse<>(200,"Lista de medicamentos",lista);
			// Devuelve la respuesta con un código HTTP 200 (OK) y la lista de medicamentos
			return ResponseEntity.status(HttpStatus.OK).body(dto);

			/*
			public DTOResponse<List<Medicamento>> mostrarTodosLosMedicamentos(){
			DTOResponse<List<Medicamento>> dto = new DTOResponse<>();
			List<Medicamento> lista = service.mostrarTodos();

			dto.setStatus(200);
			dto.setMessage(null);
			dto.setData(lista);
			return dto;*/

		}

		// Méttodo para obtener un medicamento específico por su ID
		@GetMapping("/medicamentos/{id}")
		public ResponseEntity<DTOResponse<Medicamento>> mostrarMedicamentosPorId(@PathVariable("id") Long id) {
			Medicamento medicamento = service.mostrarPorId(id);
			// Si el medicamento existe, se devuelve el objeto en un DTO y un estatus 200 (OK)
			if (medicamento != null) {
				DTOResponse<Medicamento> dto = new DTOResponse<>(200, "",  medicamento);
				return ResponseEntity.status(HttpStatus.OK).body(dto);
			} else {
				// Si el medicamento no existe, se lanza una excepción personalizada, que será manejada por GlobalExceptionHandler
				throw new ElementoNoExisteException("El medicamento con id "+ id+" no existe");
			}

			/*if (service.existe(id)) {
				DTOResponse<Medicamento> dto = new DTOResponse<>(200, "", service.mostrarPorId(id));
				return ResponseEntity.status(HttpStatus.OK).body(dto);
			}else{
				DTOResponse<Medicamento> dto = new DTOResponse<>(404, "El medicamento buscado no existe", null);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
			}*/

		}

		/*
		public DTOResponse<Medicamento> mostrarMedicamentosPorId(@PathVariable("id") Long id){
			DTOResponse<Medicamento> dto = new DTOResponse<>();
			if (service.existe(id)){
				dto.setStatus(200);
				dto.setData(service.mostrarPorId(id));
			}else{
				dto.setStatus(404);
				List<String> errores = new ArrayList<>();
				errores.add("Este medicamento no existe");
				dto.setMessage(errores);
			}
			return dto;
		}*/

		// Méttodo para crear un nuevo medicamento
		@PostMapping("/medicamentos")
		public ResponseEntity<DTOResponse<Medicamento>> crearRegistro(@Valid @RequestBody Medicamento medicamento){
		//La notación @Valid activará la validación de los datos recibidos en las solicitudes. Si los datos no cumplen con las restricciones, se lanzará una ConstraintViolationException o MethodArgumentNotValidException

			// Verifica si el medicamento ya existe usando su ID
			if(service.existe(medicamento.getId())){
				/*DTOResponse<Medicamento> dto = new DTOResponse<>(500,"El identificador de este medicamento" + medicamento.getId().toString() + " ya existe",null);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);*/

				// Si el medicamento ya existe, se lanza una excepción
			throw new ElementeYaExisteException("El medicamento ya existe");
			}else {
				// Si no existe, se crea un objeto DTO para guardar el medicamento. Se usa estatus 201 (CREATED)
				DTOResponse<Medicamento> dto = new DTOResponse<Medicamento>(201,"Medicamento agregado correctamente",service.guardar(medicamento));
				return ResponseEntity.status(HttpStatus.CREATED).body(dto);
			}
		}

		// Méttodo para actualizar un medicamento existente
		@PutMapping("/medicamentos")
		public ResponseEntity<DTOResponse<Medicamento>> actualizarRegistro(@Valid @RequestBody Medicamento medicamento){
			// Verifica si el medicamento existe usando su ID.
			if(service.existe(medicamento.getId())) {

				//Si existe, se actualiza el medicamento con dto y un estatus 200 (OK)
				DTOResponse<Medicamento> dto = new DTOResponse<>(200, "", service.guardar(medicamento));
				return ResponseEntity.status(HttpStatus.OK).body(dto);
			}else{
				/*DTOResponse<Medicamento> dto = new DTOResponse<>(404, "El id "+medicamento.getId().toString()+ " no existe", null);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);*/

				throw new ElementoNoExisteException("El medicamento a actualizar no existe");
				// Si no existe, se lanza una excepción indicando que no se encontró el medicamento
			}
		}

		// Méttodo para eliminar un medicamento por su ID
		@DeleteMapping("/medicamentos/{id}")
		public ResponseEntity<DTOResponse<?>> eliminarRegistro(@PathVariable("id") Long id){
			if (service.existe(id)){

				//Verifica si el medicamento existe y se elimina el medicamento
				service.eliminar(id);
				DTOResponse<?> dtoSi = new DTOResponse<>(200, "Medicamento eliminado correctamente", null);
				return ResponseEntity.status(HttpStatus.OK).body(dtoSi);
			}else {
				/*DTOResponse<?> dtoNo = new DTOResponse<>(400, "El medicamento a aliminar no existe", null);
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dtoNo);*/

				// Si no existe, se lanza una excepción indicando que no se encontró
				throw new ElementoNoExisteException("El medicamento a aliminar no existe");
			}
		}


		//Validaciones de entrada de datos enviada a GlobalExceptionHandler
		/*@ExceptionHandler(ConstraintViolationException.class)
		DTOResponse<Medicamento> controladorExcepciones (ConstraintViolationException e){
			List<String> errors = new ArrayList<>();
			for (ConstraintViolation<?> violation : e.getConstraintViolations()){
				errors.add(violation.getMessage());
			}
			DTOResponse<Medicamento> response = new DTOResponse<>(404,errors,null);
			return response;

		}*/
	}




