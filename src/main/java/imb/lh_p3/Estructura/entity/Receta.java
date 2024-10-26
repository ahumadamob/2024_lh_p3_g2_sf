package imb.lh_p3.Estructura.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Receta {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
private String turno;
private String paciente;
private LocalDate fecha;
private String obraSocial;
private String medicamento;

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getTurno() {
	return turno;
}
public void setTurno(String turno) {
	this.turno = turno;
}
public String getPaciente() {
	return paciente;
}
public void setPaciente(String paciente) {
	this.paciente = paciente;
}
public String getObraSocial() {
	return obraSocial;
}
public void setObraSocial(String obraSocial) {
	this.obraSocial = obraSocial;
}
public String getMedicamento() {
	return medicamento;
}
public void setMedicamento(String medicamento) {
	this.medicamento = medicamento;
}

}
