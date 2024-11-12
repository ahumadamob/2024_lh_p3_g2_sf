package MGException;

public class VerificaExistencia extends RuntimeException  {
	
	
    public VerificaExistencia(String message) {
        super(message);
    }

    public VerificaExistencia(String message, Throwable cause) {
        super(message, cause);
    }


}
