package imb.lh_p3.estructura.exceptions;

public class ElementoNoExisteException extends RuntimeException {

    // Constructor que acepta un mensaje personalizado
    public ElementoNoExisteException(String message) {
        super(message);
    }

    // Constructor opcional que acepta un mensaje y una causa
    public ElementoNoExisteException(String message, Throwable cause) {
        super(message, cause);
    }

}