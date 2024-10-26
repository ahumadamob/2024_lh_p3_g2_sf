package imb.lh_p3.estructura.util;

import java.util.ArrayList;
import java.util.List;

//DTO: objeto usado para transportar datos entre las capas de la aplicación.
public class DTOResponse<T> {

	//atributos
	private int status;
	private List<String> message;
	private T data;

	//constructores
	public DTOResponse(int status, List<String> message, T data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public DTOResponse(int status, String message, T data) {
		super();
		this.status = status;
		List<String> messages = new ArrayList();
		messages.add(message);
		this.message = messages;
		this.data = data;
	}

	public DTOResponse() {

	}

	//getter y setters
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<String> getMessage() {
		return message;
	}
	public void setMessage(List<String> message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
