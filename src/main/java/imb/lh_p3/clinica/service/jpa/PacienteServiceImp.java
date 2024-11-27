package imb.lh_p3.clinica.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.lh_p3.clinica.entity.Paciente;
import imb.lh_p3.clinica.repository.PacienteRepository;
import imb.lh_p3.clinica.service.PacienteService;

@Service
public class PacienteServiceImp implements PacienteService {
	@Autowired
	private PacienteRepository repositorio;

	@Override
	public List<Paciente> mostrarTodos() {
		return repositorio.findAll();
	}

	@Override
	public Paciente mostrarPorId(Long id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public Paciente guardar(Paciente paciente) {
			return repositorio.save(paciente);

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
