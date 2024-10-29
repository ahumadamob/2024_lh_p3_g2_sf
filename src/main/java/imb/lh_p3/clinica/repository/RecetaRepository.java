package imb.lh_p3.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import imb.lh_p3.clinica.entity.Receta;

@Repository
public interface RecetaRepository extends JpaRepository<Receta,Long> {

}
