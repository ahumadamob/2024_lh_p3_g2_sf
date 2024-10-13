package imb.lh_p3.Estructura.repository;

import imb.lh_p3.Estructura.Entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PacienteRepository  extends JpaRepository<Paciente, Long>{

}
