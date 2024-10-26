package imb.lh_p3.Estructura.service.jpa;

import imb.lh_p3.Estructura.Entity.Paciente;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.lh_p3.Estructura.repository.PacienteRepository;
import imb.lh_p3.Estructura.service.PacienteService;

@Service
public class PacienteServiceImp implements PacienteService {

	@Autowired
	private PacienteRepository repo;
	
	@Override
	public List<Paciente> mostrarTodos() {
		
		return repo.findAll();
	}

	@Override
	public Paciente mostrarPorId(Long id) {
		
		return repo.findById(id).orElse(null);
	}

	@Override
	public Paciente guardar(Paciente paciente) {
		
		return repo.save(paciente);
	}

	@Override
	public void eliminar(Long id) {
		repo.deleteById(id);
		
	}

	@Override
	public boolean existe(Long id) {
		if (id==null) {
			return false;
		} else {
			return repo.existsById(id);
		}
		
	}

    public Object guardar(Object paciente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
