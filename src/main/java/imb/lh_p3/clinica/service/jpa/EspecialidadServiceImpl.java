package imb.lh_p3.clinica.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.lh_p3.clinica.entity.Especialidad;
import imb.lh_p3.clinica.repository.EspecialidadRepository;
import imb.lh_p3.clinica.service.IEspecialidadService;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService{
	@Autowired
	private EspecialidadRepository repositorio;

	@Override
	public List<Especialidad> mostrarTodos() {
		return repositorio.findAll();
	}

	@Override
	public Especialidad mostrarPorId(Long id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public Especialidad guardar(Especialidad especialidad) {

			return repositorio.save(especialidad);
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
