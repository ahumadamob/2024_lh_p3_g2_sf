/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package imb.lh_p3.clinica.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<DTOResponse<String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
		DTOResponse<String> response = new DTOResponse<>(404, ex.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<DTOResponse<List<String>>> handleConstraintViolationException(
			ConstraintViolationException e) {
		List<String> errors = new ArrayList<>();
		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
			errors.add(violation.getMessage());
		}
		DTOResponse<List<String>> response = new DTOResponse<>(400, errors, null);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<DTOResponse<List<String>>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {
		List<String> errors = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getDefaultMessage());
		}
		DTOResponse<List<String>> response = new DTOResponse<>(400, errors, null);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
