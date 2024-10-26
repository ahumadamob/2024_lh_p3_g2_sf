package imb.lh_p3.Estructura.controller;


import imb.lh_p3.Estructura.Entity.Receta;
import imb.lh_p3.Estructura.repository.RecetaRepository;
import imb.lh_p3.Estructura.service.IRecetaService;
import imb.lh_p3.Estructura.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecetaController {
    @Autowired
    IRecetaService service;

    @GetMapping("/recetas")
    ApiResponse<List<Receta>> mostrarTodos() {
        ApiResponse<List<Receta>> response = new ApiResponse<>();
        List<Receta> lista = service.mostrarTodos();

        if (lista.isEmpty()) {
            response.setError("No se encuentran registros");
        } else {
            response.setData(lista);
        }

        return response;
    }

    @GetMapping("/recetas/{id}")
    ApiResponse<Receta> mostrarPorId(@PathVariable("id") Long id) {
        ApiResponse<Receta> response = new ApiResponse<>();
        Receta receta = service.mostrarPorId(id);

        if (receta == null) {
            response.setError("No exite el ID: " + id.toString());
        } else {
            response.setData(receta);
        }
        return response;

    }

    @PostMapping("/receta")
    ApiResponse<Receta> crearRegistro(@RequestBody Receta receta) {
        ApiResponse<Receta> response = new ApiResponse<>();
        if (service.existe(receta.getId())) {
            response.setError("Ya existe este registro");
        } else {
            Receta recetaGuardado = service.guardar(receta);
            response.setData(recetaGuardado);
        }
        return response;
    }

    @PutMapping("/medicos")
    ApiResponse<Receta> actualizarRegistro(@RequestBody Receta receta) {
        ApiResponse<Receta> response = new ApiResponse<>();
        if (service.existe(receta.getId())) {
            Receta medicoGuardado = service.guardar(receta);
            response.setData(medicoGuardado);
        } else {
            response.setError("El registro no existe");
        }
        return response;
    }

    @DeleteMapping("/recetas/{id}")
    String eliminarRegistro(@PathVariable("id") Long id) {
        if (service.existe(id)) {
            service.eliminar(id);
            return "El registro se eliminó correctamente";
        } else {
            return "El id no existe";
        }


    }
}