package imb.lh_p3.clinica.service.jpa;

import imb.lh_p3.clinica.entity.Pedido;
import imb.lh_p3.clinica.repository.PedidoRepository;
import imb.lh_p3.clinica.service.IPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImpl implements IPedidoService {
    @Autowired
    private PedidoRepository repositorio;

    @Override
    public List<Pedido> mostrarTodos() {
        return repositorio.findAll();
    }

    @Override
    public Pedido mostrarPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Pedido guardar(Pedido pedido) {
        return repositorio.save(pedido);
    }

    @Override
    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        if(id==null){
            return false;
        }else{
            return repositorio.existsById(id);
        }
    }
}
