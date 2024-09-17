package imb.lh_p3.Estructura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.lh_p3.Estructura.entity.Paciente;

public interface PacienteRepository  extends JpaRepository<Paciente, Long>{

}
