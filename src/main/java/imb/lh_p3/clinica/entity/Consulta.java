package imb.lh_p3.clinica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
	public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idConsulta;


	@OneToOne
	@JoinColumn(name = "idTurno")
	private Turno turno;


	@OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Pedido> pedidos;

	@NotNull(message = "Debe completar un diagnostico")
	@NotBlank(message = "El Diagnostico no puede estar en blanco")
	@Min(value = 5, message = "Describa el diagnostico del paciente")
	private String diagnostico;

	@NotNull(message = "Debe completar un tratamiento")
	@NotBlank(message = "El tratamiento no puede estar en blanco")
	@Min(value = 5, message = "Describa el tratamiento del paciente")
	private String tratamiento;

	public Long getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(Long idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public @NotNull(message = "Debe completar un diagnostico") @NotBlank(message = "El Diagnostico no puede estar en blanco") @Min(value = 5, message = "Describa el diagnostico del paciente") String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(@NotNull(message = "Debe completar un diagnostico") @NotBlank(message = "El Diagnostico no puede estar en blanco") @Min(value = 5, message = "Describa el diagnostico del paciente") String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public @NotNull(message = "Debe completar un tratamiento") @NotBlank(message = "El tratamiento no puede estar en blanco") @Min(value = 5, message = "Describa el tratamiento del paciente") String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(@NotNull(message = "Debe completar un tratamiento") @NotBlank(message = "El tratamiento no puede estar en blanco") @Min(value = 5, message = "Describa el tratamiento del paciente") String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
}
