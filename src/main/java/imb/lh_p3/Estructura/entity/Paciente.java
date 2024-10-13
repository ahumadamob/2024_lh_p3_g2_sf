package imb.lh_p3.Estructura.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Paciente {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String apellido;
	private String DNI;
//private ObraSocial obraSocial;
	private String Domicilio;
	private String Telefono;
	private int idHistoriaClinica;
	private String correo;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getDomicilio() {
		return Domicilio;
	}

	public void setDomicilio(String domicilio) {
		Domicilio = domicilio;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	public int getIdHistoriaClinica() {
		return idHistoriaClinica;
	}

	public void setIdHistoriaClinica(int idHistoriaClinica) {
		this.idHistoriaClinica = idHistoriaClinica;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

}
