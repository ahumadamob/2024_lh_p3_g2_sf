package imb.lh_p3.clinica.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import imb.lh_p3.clinica.entity.Receta;
import imb.lh_p3.clinica.service.IRecetaService;
import imb.lh_p3.clinica.util.ApiResponse;

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

    @PutMapping("/receta")
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

    @DeleteMapping("/receta/{id}")
    String eliminarRegistro(@PathVariable("id") Long id) {
        if (service.existe(id)) {
            service.eliminar(id);
            return "El registro se eliminó correctamente";
        } else {
            return "El id no existe";
        }


    }
}