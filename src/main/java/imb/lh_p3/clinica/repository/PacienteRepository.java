package imb.lh_p3.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.lh_p3.clinica.entity.Paciente;

import java.util.List;

public interface PacienteRepository  extends JpaRepository<Paciente, Long>{


}
