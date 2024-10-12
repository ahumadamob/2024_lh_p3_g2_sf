package imb.lh_p3.Estructura.service;

import java.util.List;

import imb.lh_p3.Estructura.entity.Paciente;

public interface PacienteService {
	public List<Paciente>mostrarTodos();
	public Paciente mostrarPorId(Long id);
	public Paciente guardar(Paciente paciente);
	public void eliminar(Long id);
	public boolean existe (Long id);
}