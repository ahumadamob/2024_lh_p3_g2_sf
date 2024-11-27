package imb.lh_p3.clinica.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.lh_p3.clinica.entity.Medicamento;
import imb.lh_p3.clinica.repository.MedicamentoRepository;
import imb.lh_p3.clinica.service.IMedicamentoService;

@Service
public class MedicamentoServiceImpl implements IMedicamentoService{
	@Autowired
	private MedicamentoRepository repositorio;

	@Override
	public List<Medicamento> mostrarTodos() {
		return repositorio.findAll();
	}

	@Override
	public Medicamento mostrarPorId(Long id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public Medicamento guardar(Medicamento medicamento) {
		return repositorio.save(medicamento);
	}

	@Override
	public void eliminar(Long id) {
		repositorio.deleteById(id);
	}

	@Override
	public boolean existe(Long id) {
		if(id==null){
			return false;
		}else{
			return repositorio.existsById(id);
		}
	}



}
