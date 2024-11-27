package imb.lh_p3.clinica.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pedido {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedido;

	private Long idConsulta;

	@ManyToOne
	@JoinColumn(name = "idConsulta",  insertable = false, updatable = false)
	@JsonIgnoreProperties("pedidos")
	private Consulta consulta;


	@NotNull(message = "Debe completar el tipo de pedido")
	@NotBlank(message = "El tipo de pedido no puede estar en blanco")
	@Min(value = 2, message = "Escriba un tipo de pedido valido")
	private String tipo;

	@NotNull(message = "Debe describir porque solicita estos estudios")
	@NotBlank(message = "Informe no puede estar en blanco")
	@Min(value = 3, message = "Descripcion muy corta")
	private String informe;

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Long getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(Long idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getInforme() {
		return informe;
	}

	public void setInforme(String informe) {
		this.informe = informe;
	}
}