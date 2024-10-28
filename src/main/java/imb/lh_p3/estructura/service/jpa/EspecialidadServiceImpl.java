package imb.lh_p3.estructura.service.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.lh_p3.estructura.entity.Especialidad;
import imb.lh_p3.estructura.repository.EspecialidadRepository;
import imb.lh_p3.estructura.service.IEspecialidadService;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService{
	
	@Autowired
	private EspecialidadRepository repo;
	
	@Override
	public List<Especialidad> mostrarTodos() {
		
		
		return repo.findAll();
		
	}

	@Override
	public Especialidad mostrarPorId(Long id) {
		
		return repo.findById(id).orElse(null);

	}

	@Override
	public Especialidad guardar(Especialidad especialidad) {
		return repo.save(especialidad);
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
