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
		private ConsultaRepository repo;

		@Override
		public List<Consulta> mostrarTodos() {

			return repo.findAll();
		}

		@Override
		public Consulta mostrarPorId(Long id) {

			return repo.findById(id).orElse(null);
		}

		@Override
		public Consulta guardar(Consulta consulta) {

			return repo.save(consulta);
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

	}

