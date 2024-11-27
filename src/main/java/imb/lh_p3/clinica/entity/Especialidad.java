package imb.lh_p3.clinica.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Especialidad {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idEspecialidad;

	@NotNull(message = "El nombre de la especialidad es obligatorio")
	@NotBlank(message = "El nombre de la especialidad no puede estar en blanco")
	@Size(min = 2, max = 100, message = "El nombre debe tener como minimo 2 caracteres")
	private String nombre;

	@OneToMany(mappedBy = "especialidad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Medico> medicos;

	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public Long getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(Long idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
