package MGException;

public class VerificaLaNoExistencia extends RuntimeException {

	
   
    public VerificaLaNoExistencia(String message) {
        super(message);
    }

    public VerificaLaNoExistencia(String message, Throwable cause) {
        super(message, cause);
    }
}
