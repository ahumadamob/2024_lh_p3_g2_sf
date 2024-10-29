package imb.lh_p3.clinica.service;

import java.util.List;

import imb.lh_p3.clinica.entity.Receta;

public interface IRecetaService {

    public List<Receta>mostrarTodos();
    public Receta mostrarPorId(Long id);
    public Receta guardar(Receta receta);
    public void eliminar(Long id);
    public boolean existe(Long id);

}
