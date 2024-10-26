package imb.lh_p3.Estructura.repository;

import imb.lh_p3.Estructura.Entity.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository extends JpaRepository<Receta,Long> {

}
