package imb.lh_p3.Estructura.service.jpa;

import imb.lh_p3.Estructura.Entity.Receta;
import imb.lh_p3.Estructura.repository.RecetaRepository;
import imb.lh_p3.Estructura.service.IRecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetaService implements IRecetaService {
    @Autowired
    RecetaRepository repo;

    @Override
    public List<Receta> mostrarTodos() {
        return repo.findAll();
    }

    @Override
    public Receta mostrarPorId(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Receta guardar(Receta receta) {
        return repo.save(receta);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        if(id==null) {
            return false;
        }else {
            return repo.existsById(id);
        }
    }
}
