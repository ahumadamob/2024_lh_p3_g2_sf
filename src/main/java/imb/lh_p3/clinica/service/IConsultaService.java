package imb.lh_p3.clinica.service;

import java.util.List;

import imb.lh_p3.clinica.entity.Consulta;

public interface IConsultaService {
	    public List<Consulta>mostrarTodos();
	    public Consulta mostrarPorId(Long id);
	    public Consulta guardar(Consulta consulta);
	    public void eliminar(Long id);
	    public boolean existe(Long id);
}
