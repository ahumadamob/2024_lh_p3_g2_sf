package imb.lh_p3.clinica.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Paciente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPaciente;

	@NotNull(message = "El nombre es obligatorio")
	@NotBlank(message = "El nombre no puede estar en blanco")
	@Size(min = 2, max = 100, message = "El nombre debe tener como minimo 2 caracteres")
	private String nombre;

	@NotNull(message = "El apellido es obligatorio")
	@NotBlank(message = "El apellido no puede estar en blanco")
	@Size(min = 2, max = 100, message = "El apellido debe tener como minimo 2 caracteres")
	private String apellido;

	@NotNull(message = "El DNI es obligatorio")
	@Size(min = 8, max = 10, message = "El DNI debe tener entre 8 y 10 caracteres")
	private String dni;

	private Long idObraSocial;

	private String domicilio;

	@NotNull(message = "El telefono es obligatorio")
	@Size(min = 7, message = "Numero de telefono invalido, debe tener una longitud minima de 7")
	private String telefono;

	@NotNull(message = "El correo es obligatorio")
	@Email(message = "Debe proporcionar un correo electrónico válido")
	private String correo;

	@ManyToOne()
	@JoinColumn(name = "idObraSocial",  insertable = false, updatable = false)
	@JsonIgnoreProperties("pacientes")
	private ObraSocial obraSocial;

	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("paciente") // Ignora el paciente en los turnos
	private List<Turno> turnos;

	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("paciente") //
	private List<Receta> recetas;

	public Long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Long getIdObraSocial() {
		return idObraSocial;
	}

	public void setIdObraSocial(Long idObraSocial) {
		this.idObraSocial = idObraSocial;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public ObraSocial getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public List<Receta> getRecetas() {
		return recetas;
	}

	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}
}
