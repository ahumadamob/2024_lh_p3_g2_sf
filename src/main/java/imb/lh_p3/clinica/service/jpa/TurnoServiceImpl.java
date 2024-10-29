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
	private TurnoRepository repo;

	@Override
	public List<Turno> mostrarTodos() {
		return repo.findAll();

	}

	@Override
	public Turno mostrarPorId(Long id) {
		return repo.findById(id).orElse(null);

	}

	@Override
	public Turno guardar(Turno turno) {
		return repo.save(turno);
	}


	@Override
	public void eliminar(Long id) {
		repo.deleteById(id);
		// TODO Auto-generated method stub

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
