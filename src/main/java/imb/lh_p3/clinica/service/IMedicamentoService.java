package imb.lh_p3.clinica.service;

import java.util.List;

import imb.lh_p3.clinica.entity.Medicamento;

public interface IMedicamentoService {
	public List<Medicamento> mostrarTodos();
	public Medicamento mostrarPorId(Long id);
	public Medicamento guardar(Medicamento medicamento);
	public void eliminar(Long id);
	public boolean existe(Long id);

}
