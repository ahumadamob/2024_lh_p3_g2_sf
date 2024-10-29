package imb.lh_p3.clinica.service;

import java.util.List;

import imb.lh_p3.clinica.entity.Medico;

public interface IMedicoService {

	public List<Medico> mostrarTodos();
	public Medico mostrarPorId(Long id);
	public Medico guardar(Medico medico);
	public void eliminar(Long id);
	public boolean existe(Long id);

}
