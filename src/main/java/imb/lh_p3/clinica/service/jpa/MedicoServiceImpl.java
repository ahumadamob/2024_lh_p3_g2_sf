package imb.lh_p3.clinica.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.lh_p3.clinica.entity.Medico;
import imb.lh_p3.clinica.repository.MedicoRepository;
import imb.lh_p3.clinica.service.IMedicoService;

@Service
public class MedicoServiceImpl implements IMedicoService{
	@Autowired
	private MedicoRepository repositorio;

	@Override
	public List<Medico> mostrarTodos() {
		return repositorio.findAll();
	}

	@Override
	public Medico mostrarPorId(Long id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public Medico guardar(Medico medico) {

		return repositorio.save(medico);

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
