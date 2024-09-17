package imb.lh_p3.Estructura.service;

import java.util.List;


import imb.lh_p3.Estructura.Entity.Especialidad;

public interface IEspecialidadService {
	public List<Especialidad>mostrarTodos();
	public Especialidad mostrarPorId(Long id);
	public Especialidad guardar(Especialidad especialidad);
	public void eliminar(Long id);
	public boolean existe(Long id);

}
