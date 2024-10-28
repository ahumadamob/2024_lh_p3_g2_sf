package imb.lh_p3.Estructura.service;

import imb.lh_p3.Estructura.Entity.Receta;


import java.util.List;


public interface IRecetaService {

    public List<Receta>mostrarTodos();
    public Receta mostrarPorId(Long id);
    public Receta guardar(Receta receta);
    public void eliminar(Long id);
    public boolean existe(Long id);

}
