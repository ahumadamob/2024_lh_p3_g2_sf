	package imb.lh_p3.clinica;
	import imb.lh_p3.clinica.exceptions.ElementoNoExisteException;
	import imb.lh_p3.clinica.exceptions.ElementeYaExisteException;
	import imb.lh_p3.clinica.util.DTOResponse;
	import jakarta.validation.ConstraintViolation;
	import jakarta.validation.ConstraintViolationException;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.MethodArgumentNotValidException;
	import org.springframework.web.bind.annotation.ControllerAdvice;
	import org.springframework.web.bind.annotation.ExceptionHandler;
	
	import java.util.ArrayList;
	import java.util.List;
	
	import org.springframework.http.HttpStatus;
	
	
	
	@ControllerAdvice  // Esta anotación permite aplicar el manejo de excepciones a todos los controladores en la aplicación
	public class GlobalExceptionHandler {
	
	    // Manejador para excepciones cuando las validaciones fallan a nivel de repositorio o servicio
	    @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<DTOResponse<?>> handleConstraintViolationException(ConstraintViolationException ex) {
	        List<String> errors = new ArrayList<>();
	
	       // Itera sobre las violaciones y añade el mensaje de cada una al listado de errores
	        for (ConstraintViolation<?> violation : ex.getConstraintViolations()){
	            errors.add(violation.getMessage());
	        }
	        // Crea una respuesta DTO personalizada con el código 400 (BAD_REQUEST)y los errores
	        DTOResponse<?> response = new DTOResponse<>(HttpStatus.BAD_REQUEST.value(), errors, null);
	
	        // Devuelve el dto y el estatus http
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	
	    //// Manejador para excepciones lanzadas cuando fallan las validaciones en el cuerpo de la solicitud
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<DTOResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
	        List<String> errorMessages = new ArrayList<>();
	
	        ex.getBindingResult().getFieldErrors().forEach(error ->
	                errorMessages.add(error.getField() + ": " + error.getDefaultMessage())
	        );
	        DTOResponse<Object> response = new DTOResponse<>(HttpStatus.BAD_REQUEST.value(), errorMessages,null);
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	
	
	    // Manejador para excepciones genéricas, permite capturar errores no específicos
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<DTOResponse<?>> handleGeneralException(Exception ex) {
	        List<String> errors = new ArrayList<>();
	        errors.add("Ocurrió un error inesperado: " + ex.getMessage());
	
	        // Crea la respuesta con el mensaje de error general y el código de estado 500 (INTERNAL_SERVER_ERROR)
	        DTOResponse<?> response = new DTOResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), errors, null);
	
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	
	    // Manejador para excepciones específicas cuando un recurso no existe en la base de datos
	    @ExceptionHandler(ElementoNoExisteException.class)
	    public ResponseEntity<DTOResponse<?>> handleElementoNoExiste(ElementoNoExisteException ex) {
	        // Creamos el DTO con código 404 (Not Found)
	        DTOResponse<?> response = new DTOResponse<>(HttpStatus.NOT_FOUND.value(), List.of(ex.getMessage()), null);
	        // Enviamos el código de estatus junto con el DTO
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }
	
	    // Manejador para excepciones cuando un recurso ya existe en la base de datos
	    @ExceptionHandler(ElementeYaExisteException.class)
	    public ResponseEntity<DTOResponse<?>> manejarRecursoYaExiste(ElementeYaExisteException ex) {
	        // Creamos el DTO con código 409 (Conflict)
	        DTOResponse<?> response = new DTOResponse<>(HttpStatus.CONFLICT.value(), List.of(ex.getMessage()), null);
	        // Enviamos el código de estatus junto con el DTO
	        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	    }
	
	
	
	
	}
