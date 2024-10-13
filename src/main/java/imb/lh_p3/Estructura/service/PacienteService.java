package imb.lh_p3.Estructura.service;

import imb.lh_p3.Estructura.Entity.Paciente;
import java.util.List;


public interface PacienteService {
	public List<Paciente>mostrarTodos();
	public Paciente mostrarPorId(Long id);
	public Paciente guardar(Paciente paciente);
	public void eliminar(Long id);
	public boolean existe (Long id);
}
