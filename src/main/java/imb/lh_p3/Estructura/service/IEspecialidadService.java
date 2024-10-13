package imb.lh_p3.Estructura.service;

import imb.lh_p3.Estructura.Entity.Especialidad;
import java.util.List;


public interface IEspecialidadService {
	public List<Especialidad>mostrarTodos();
	public Especialidad mostrarPorId(Long id);
	public Especialidad guardar(Especialidad especialidad);
	public void eliminar(Long id);
	public boolean existe(Long id);

}
