package imb.lh_p3.clinica.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.lh_p3.clinica.entity.Receta;
import imb.lh_p3.clinica.repository.RecetaRepository;
import imb.lh_p3.clinica.service.IRecetaService;

@Service
public class RecetaServiceImpl implements IRecetaService {

    @Autowired
    private RecetaRepository repositorio;

    @Override
    public List<Receta> mostrarTodos() {
        return repositorio.findAll();
    }

    @Override
    public Receta mostrarPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Receta guardar(Receta receta) {
        return repositorio.save(receta);
    }

    @Override
    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        if (id == null) {
            return false;
        } else {
            return repositorio.existsById(id);
        }
    }
}
