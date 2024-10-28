package imb.lh_p3.estructura.service.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.lh_p3.estructura.entity.Medicamento;
import imb.lh_p3.estructura.repository.MedicamentoRepository;
import imb.lh_p3.estructura.service.IMedicamentoService;

@Service
public class MedicamentoServiceImpl implements IMedicamentoService{
	
	@Autowired
	private MedicamentoRepository repo;
	
	@Override
	public List<Medicamento> mostrarTodos() {
		
		
		return repo.findAll();
		
	}

	@Override
	public Medicamento mostrarPorId(Long id) {
		
		return repo.findById(id).orElse(null);

	}

	@Override
	public Medicamento guardar(Medicamento medicamento) {
		return repo.save(medicamento);
	}

	@Override
	public void eliminar(Long id) {
		repo.deleteById(id);
		
		
	}

	@Override
	public boolean existe(Long id) {
		if(id==null) {
			return false;
		}else {
			return repo.existsById(id);
		}
		
	}

	
	
}
