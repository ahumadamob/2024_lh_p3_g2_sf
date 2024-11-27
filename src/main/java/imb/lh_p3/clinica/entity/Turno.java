package imb.lh_p3.clinica.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Turno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Debe indicar un paciente")
	private Long idPaciente;

	@NotNull(message = "Debe indicar un medico")
	private Long idMedico;

	private LocalDateTime fecha;

	@NotNull(message = "Indicar lugar es obligatorio")
	@NotBlank(message = "El lugar no puede estar en blanco")
	@Size(min = 2, max = 500, message = "El lugar indicado no contine informacion valida")
	private String lugar;

	private Boolean estado;

	@ManyToOne()
	@JoinColumn(name = "idPaciente", insertable = false, updatable = false)
	@JsonIgnoreProperties("turnos") // Ignora los turnos del paciente
	private Paciente paciente;

	@ManyToOne()
	@JoinColumn(name = "idMedico", insertable = false, updatable = false)
	@JsonIgnoreProperties("turnos")
	private Medico medico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public @NotNull(message = "Debe indicar un paciente") Long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(@NotNull(message = "Debe indicar un paciente") Long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public @NotNull(message = "Debe indicar un medico") Long getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(@NotNull(message = "Debe indicar un medico") Long idMedico) {
		this.idMedico = idMedico;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public @NotNull(message = "Indicar lugar es obligatorio") @NotBlank(message = "El lugar no puede estar en blanco") @Size(min = 2, max = 500, message = "El lugar indicado no contine informacion valida") String getLugar() {
		return lugar;
	}

	public void setLugar(@NotNull(message = "Indicar lugar es obligatorio") @NotBlank(message = "El lugar no puede estar en blanco") @Size(min = 2, max = 500, message = "El lugar indicado no contine informacion valida") String lugar) {
		this.lugar = lugar;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
}


