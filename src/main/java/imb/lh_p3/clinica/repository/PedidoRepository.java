package imb.lh_p3.clinica.repository;

import imb.lh_p3.clinica.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
