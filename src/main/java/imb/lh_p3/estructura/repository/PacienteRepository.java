package imb.lh_p3.estructura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.lh_p3.estructura.entity.Paciente;

public interface PacienteRepository  extends JpaRepository<Paciente, Long>{

}
