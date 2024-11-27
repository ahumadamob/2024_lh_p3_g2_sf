package imb.lh_p3.clinica.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.lh_p3.clinica.entity.Consulta;
import imb.lh_p3.clinica.repository.ConsultaRepository;
import imb.lh_p3.clinica.service.IConsultaService;

@Service
public class ConsultaServiceImpl  implements IConsultaService {

	@Autowired
	private ConsultaRepository repositorio;

	@Override
	public List<Consulta> mostrarTodos() {
		return repositorio.findAll();
	}

	@Override
	public Consulta mostrarPorId(Long id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public Consulta guardar(Consulta consulta) {
		return repositorio.save(consulta);
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

