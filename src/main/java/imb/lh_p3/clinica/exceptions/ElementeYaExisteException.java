	package imb.lh_p3.clinica.exceptions;
	
	public class ElementeYaExisteException extends RuntimeException {
	
	    // Constructor que acepta un mensaje personalizado
	    public ElementeYaExisteException(String message) {
	        super(message);
	    }
	
	    // Constructor opcional que acepta un mensaje y una causa
	    public ElementeYaExisteException(String message, Throwable cause) {
	        super(message, cause);
	    }
	
	}
