package imb.lh_p3.clinica.service;

import imb.lh_p3.clinica.entity.Pedido;

import java.util.List;

public interface IPedidoService {

    public List<Pedido> mostrarTodos();
    public Pedido mostrarPorId(Long id);
    public Pedido guardar(Pedido pedido);
    public void eliminar(Long id);
    public boolean existe(Long id);

}
