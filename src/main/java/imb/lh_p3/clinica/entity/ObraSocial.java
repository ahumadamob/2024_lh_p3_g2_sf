package imb.lh_p3.clinica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class ObraSocial {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idObraSocial;

	@NotNull(message = "El nombre de la obra social es obligatorio")
	@NotBlank(message = "El nombre de la obra social no puede estar en blanco")
	@Size(min = 2, max = 100, message = "El nombre debe tener como minimo 2 caracteres")
	private String nombre;


	@OneToMany(mappedBy = "obraSocial", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Paciente> pacientes;

	@OneToMany(mappedBy = "obraSocial", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("obraSocial") //
	private List<Receta> recetas;

	public Long getIdObraSocial() {
		return idObraSocial;
	}

	public void setIdObraSocial(Long idObraSocial) {
		this.idObraSocial = idObraSocial;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public List<Receta> getRecetas() {
		return recetas;
	}

	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}
}
