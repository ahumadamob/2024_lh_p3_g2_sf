package imb.lh_p3.Estructura.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Receta {
 @Id
 @GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
private String turno;
private String paciente;
private int fecha;
private String obraSocial;
private String medicamento;


public int getInteger() {
	return id;
}
public void setId(Integer id) {
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
public int getFecha() {
	return fecha;
}
public void setFecha(int fecha) {
	this.fecha = fecha;
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
