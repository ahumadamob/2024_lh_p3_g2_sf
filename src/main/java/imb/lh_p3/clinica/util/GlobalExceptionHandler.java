package imb.lh_p3.clinica.util;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import imb.lh_p3.clinica.exceptions.ElementeYaExisteException;
import imb.lh_p3.clinica.exceptions.ElementoNoExisteException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice  // Esta anotación permite aplicar el manejo de excepciones a todos los controladores en la aplicación
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<DTOResponse<List<String>>> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }

        DTOResponse<List<String>> response = new DTOResponse<>(HttpStatus.BAD_REQUEST.value(), errors, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DTOResponse<List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errorMessages.add(error.getField() + ": " + error.getDefaultMessage())
        );

        DTOResponse<List<String>> response = new DTOResponse<>(HttpStatus.BAD_REQUEST.value(), errorMessages, null);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DTOResponse<String>> handleGeneralException(Exception ex) {

        DTOResponse<String> response = new DTOResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                List.of("Ocurrió un error inesperado: " + ex.getMessage()), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ElementoNoExisteException.class)
    public ResponseEntity<DTOResponse<String>> ElementoNoExisteException(ElementoNoExisteException ex){
        DTOResponse<String> response = new DTOResponse<>(HttpStatus.NOT_FOUND.value(), List.of(ex.getMessage()), null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ElementeYaExisteException.class)
    public ResponseEntity<DTOResponse<String>> ElementeYaExisteException(ElementeYaExisteException ex){
        DTOResponse<String> response = new DTOResponse<>(HttpStatus.CONFLICT.value(), List.of(ex.getMessage()), null);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

}
