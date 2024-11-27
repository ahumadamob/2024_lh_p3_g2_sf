package imb.lh_p3.clinica.controller;


import java.util.List;

import imb.lh_p3.clinica.exceptions.ElementeYaExisteException;
import imb.lh_p3.clinica.exceptions.ElementoNoExisteException;
import imb.lh_p3.clinica.util.DTOResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import imb.lh_p3.clinica.entity.Receta;
import imb.lh_p3.clinica.service.IRecetaService;

@RestController
public class RecetaController {
    @Autowired
    private IRecetaService service;

    @GetMapping("/receta")
    public ResponseEntity<DTOResponse<List<Receta>>> mostrarTodasLasRecetas() {
        List<Receta> lista = service.mostrarTodos();
        if (lista.isEmpty()) {
            throw new ElementoNoExisteException("No hay recetas registradas");
        } else {
            DTOResponse<List<Receta>> dto = new DTOResponse<>(200, "Lista de recetas", lista);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
    }

    @GetMapping("/receta/{id}")
    public ResponseEntity<DTOResponse<Receta>> mostrarRecetaPorId(@PathVariable("id") Long id) {
        Receta receta = service.mostrarPorId(id);
        if (receta == null) {
            throw new ElementoNoExisteException("La receta con el id " + id + " no existe");
        } else {
            DTOResponse<Receta> dto = new DTOResponse<>(200, "Receta:", receta);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
    }

    @PostMapping("/receta")
    public ResponseEntity<DTOResponse<Receta>> guardarReceta(@Valid @RequestBody Receta receta) {
        if (service.existe(receta.getIdReceta())) {
            throw new ElementeYaExisteException("Esta receta ya ha sido guardada ");
        } else {
            DTOResponse<Receta> dto = new DTOResponse<>(201, "Receta guardada correctamente", service.guardar(receta));
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        }
    }

    @PutMapping("/receta")
    public ResponseEntity<DTOResponse<Receta>> actualizarReceta(@Valid @RequestBody Receta receta) {
        if (service.existe(receta.getIdReceta())) {
            DTOResponse<Receta> dto = new DTOResponse<>(200, "Receta actualizada correctamente", service.guardar(receta));
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } else {
            throw new ElementoNoExisteException("La receta que esta tratando de actualizar no existe");
        }
    }

    @DeleteMapping("/receta/{id}")
    public ResponseEntity<DTOResponse<?>> eliminarReceta(@PathVariable("id") Long id) {
        if (service.existe(id)) {
            service.eliminar(id);
            DTOResponse<?> dto = new DTOResponse<>(200, "Receta eliminada correctamente", null);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        } else {
            throw new ElementoNoExisteException("La receta a eliminar no existe");
        }
    }
}