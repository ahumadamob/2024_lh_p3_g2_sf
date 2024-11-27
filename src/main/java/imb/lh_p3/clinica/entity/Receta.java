package imb.lh_p3.clinica.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Receta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idReceta;

	private Long idPaciente;

	private Long idObraSocial;


	@ManyToOne
	@JoinColumn(name = "idPaciente", insertable = false, updatable = false)
	@JsonIgnoreProperties("recetas")
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "idPaciente", insertable = false, updatable = false)
	@JsonIgnoreProperties("recetas")
	private ObraSocial obraSocial;

	@ManyToMany
	@JoinTable(
			name = "detalle_receta",
			joinColumns = {@JoinColumn(name = "idReceta")},
			inverseJoinColumns = {@JoinColumn(name = "idMedicamento")})
	private List<Medicamento> medicamentos;

	private LocalDateTime fecha;

	public Long getIdReceta() {
		return idReceta;
	}

	public void setIdReceta(Long idReceta) {
		this.idReceta = idReceta;
	}

	public Long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Long getIdObraSocial() {
		return idObraSocial;
	}

	public void setIdObraSocial(Long idObraSocial) {
		this.idObraSocial = idObraSocial;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public ObraSocial getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
}
