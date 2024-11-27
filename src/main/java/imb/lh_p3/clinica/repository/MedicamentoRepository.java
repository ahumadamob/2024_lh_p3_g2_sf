package imb.lh_p3.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.lh_p3.clinica.entity.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    boolean existsByNombre(String nombre);

}
