package imb.lh_p3.clinica.repository;

import imb.lh_p3.clinica.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import imb.lh_p3.clinica.entity.Receta;

import java.util.List;

@Repository
public interface RecetaRepository extends JpaRepository<Receta,Long> {

}
