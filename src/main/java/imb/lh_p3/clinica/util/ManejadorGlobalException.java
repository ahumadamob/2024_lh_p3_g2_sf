package imb.lh_p3.clinica.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import MGException.VerificaExistencia;
import MGException.VerificaLaNoExistencia;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

public class ManejadorGlobalException {
	
	@ControllerAdvice 
	public class GlobalExceptionHandler {

	   
	    @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<DTOResponse<?>> handleConstraintViolationException(ConstraintViolationException ex) {
	        List<String> errors = new ArrayList<>();

	        for (ConstraintViolation<?> violation : ex.getConstraintViolations()){
	            errors.add(violation.getMessage());
	        }
	       
	        DTOResponse<?> response = new DTOResponse<>(HttpStatus.BAD_REQUEST.value(), errors, null);
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }

	      

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<DTOResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
	        List<String> errorMessages = new ArrayList<>();

	        
	        ex.getBindingResult().getFieldErrors().forEach(error ->
	                errorMessages.add(error.getField() + ": " + error.getDefaultMessage())
	        );
	      
	        DTOResponse<Object> response = new DTOResponse<>(HttpStatus.BAD_REQUEST.value(), errorMessages,null);
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	    
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<DTOResponse<?>> handleGeneralException(Exception ex) {
	        List<String> errors = new ArrayList<>();
	        errors.add("Ocurrió un error inesperado: " + ex.getMessage());

	        DTOResponse<?> response = new DTOResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), errors, null);
	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    @ExceptionHandler(VerificaLaNoExistencia.class)
	    public ResponseEntity<DTOResponse<?>> handleElementoNoExiste(VerificaLaNoExistencia ex) {
	        DTOResponse<?> response = new DTOResponse<>(HttpStatus.NOT_FOUND.value(), List.of(ex.getMessage()), null);
	    
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(VerificaExistencia.class)
	    public ResponseEntity<DTOResponse<?>> manejarRecursoYaExiste(VerificaExistencia ex) {
	        DTOResponse<?> response = new DTOResponse<>(HttpStatus.CONFLICT.value(), List.of(ex.getMessage()), null);
	        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	    }
	  }
	}
