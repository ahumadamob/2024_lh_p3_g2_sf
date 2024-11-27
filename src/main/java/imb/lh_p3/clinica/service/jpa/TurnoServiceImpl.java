package imb.lh_p3.clinica.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.lh_p3.clinica.entity.Turno;
import imb.lh_p3.clinica.repository.TurnoRepository;
import imb.lh_p3.clinica.service.ITurnoService;


@Service
public class TurnoServiceImpl implements ITurnoService {

	@Autowired
	private TurnoRepository repositorio;

	@Override
	public List<Turno> mostrarTodos() {
		return repositorio.findAll();
	}

	@Override
	public Turno mostrarPorId(Long id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public Turno guardar(Turno turno) {
		return repositorio.save(turno);
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
