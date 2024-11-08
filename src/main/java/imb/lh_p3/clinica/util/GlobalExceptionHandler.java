package imb.lh_p3.clinica.util;
import imb.lh_p3.clinica.exceptions.ElementeYaExisteException;
import imb.lh_p3.clinica.exceptions.ElementoNoExisteException;
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

    // Manejador para excepciones de violaciones de restricciones en validación (ConstraintViolationException)
    // Ocurre en validaciones a nivel de servicio o repositorio
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<DTOResponse<?>> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();

        // Itera y extrae cada mensaje de violación y lo añade a la lista de errores
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()){
            errors.add(violation.getMessage());
        }
        // Crea una respuesta DTO personalizada con el código 400 (BAD_REQUEST)y la lista de los errores
        DTOResponse<?> response = new DTOResponse<>(HttpStatus.BAD_REQUEST.value(), errors, null);

        // Devuelve el dto y el estatus http con codigo 400
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Manejador para excepciones de validación en el cuerpo de la solicitud (MethodArgumentNotValidException)
    // Suele ocurrir cuando fallan las validaciones de anotaciones como @Valid en @RequestBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DTOResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();

        // Extrae los errores de cada campo que no cumple con las restricciones y los agrega a la lista de errores
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errorMessages.add(error.getField() + ": " + error.getDefaultMessage())
        );
        // Crea un DTOResponse con el estado 400 (Bad Request) y los mensajes de error
        DTOResponse<Object> response = new DTOResponse<>(HttpStatus.BAD_REQUEST.value(), errorMessages,null);
        // Devuelve la respuesta con el código 400 y los errore
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Manejador para excepciones generales que no están definidas específicamente
    // Devuelve un error 500 (Internal Server Error) con un mensaje genérico
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DTOResponse<?>> handleGeneralException(Exception ex) {
        List<String> errors = new ArrayList<>();
        errors.add("Ocurrió un error inesperado: " + ex.getMessage());

        // Crea un DTOResponse con estado 500 (INTERNAL_SERVER_ERROR) y mensaje de error general
        DTOResponse<?> response = new DTOResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), errors, null);
        // Devuelve el estado 500 y el mensaje de error general
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Manejador para excepciones cuando un recurso no existe en la base de datos
    // Devuelve un error 404 (Not Found) cuando no se encuentra el recurso
    @ExceptionHandler(ElementoNoExisteException.class)
    public ResponseEntity<DTOResponse<?>> handleElementoNoExiste(ElementoNoExisteException ex) {
        // Creamos el DTO con código 404 (Not Found)
        DTOResponse<?> response = new DTOResponse<>(HttpStatus.NOT_FOUND.value(), List.of(ex.getMessage()), null);
        // Enviamos el código de estatus junto con el DTO
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Manejador para excepciones cuando un recurso ya existe en la base de datos
    // Devuelve un error 409 (Conflict) si se intenta crear un recurso duplicado
    @ExceptionHandler(ElementeYaExisteException.class)
    public ResponseEntity<DTOResponse<?>> manejarRecursoYaExiste(ElementeYaExisteException ex) {
        // Creamos el DTO con código 409 (Conflict)
        DTOResponse<?> response = new DTOResponse<>(HttpStatus.CONFLICT.value(), List.of(ex.getMessage()), null);
        // Enviamos el código de estatus junto con el DTO
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }




}