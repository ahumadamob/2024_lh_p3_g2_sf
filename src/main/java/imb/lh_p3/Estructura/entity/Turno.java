package imb.lh_p3.Estructura.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Turno {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)

private Long id;
private String turno;
private String paciente;
private LocalDate fecha;
private String lugar;
private boolean estado;

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

public LocalDate getFecha() {
	return fecha;
}
public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
}
public String getLugar() {
	return lugar;
}
public void setLugar(String lugar) {
	this.lugar = lugar;
}
public boolean getEstado() {
	return estado;
}
public void setEstado(boolean estado) {
	this.estado = estado;
}
}

