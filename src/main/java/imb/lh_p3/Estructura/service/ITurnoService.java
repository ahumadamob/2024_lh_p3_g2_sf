package imb.lh_p3.Estructura.service;

import java.util.List;

import imb.lh_p3.Estructura.Entity.Turno;


public interface ITurnoService {
	public List<Turno>mostrarTodos();
	public Turno mostrarPorId(Long id);
	public Turno guardar(Turno turno);
	public void eliminar(Long id);
	public boolean existe(Long id);
	
}