/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package imb.lh_p3.clinica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import imb.lh_p3.clinica.entity.ObraSocial;
import imb.lh_p3.clinica.service.IObraSocialService;
import imb.lh_p3.clinica.util.ApiResponse;
import imb.lh_p3.clinica.util.ResourceNotFoundException;

/**
 *
 * @author Christian
 */
@Controller("/")
public class ObraSocialController {
 /*   
    @Autowired
    private IObraSocialService obraSocialService; 
    
    @GetMapping("/obraSocial")
public ApiResponse<List<ObraSocial>> mostrarTodosLasObrasSociales(){
		ApiResponse<List<ObraSocial>> response = new ApiResponse<>();
		List<ObraSocial> lista = obraSocialService.mostrarTodos();
		
		if (lista.isEmpty()) {
			response.setError("No existe ningún obra social.");
		} else {
			response.setData(lista);
		}
		return response;
	}
@GetMapping ("/obraSocial/{id}")
public	ApiResponse<ObraSocial> mostrarObraSocialPorId(@PathVariable("id")Long id){
		ApiResponse <ObraSocial> response = new ApiResponse <>();
		ObraSocial obraSocial = obraSocialService.mostrarPorId(id);
		
		if (obraSocial==null) {
			response.setError("No existe el ID "+ id.toString());
		} else {
			response.setData(obraSocial);
		}
		
		return response;
	}
	@PostMapping("/obraSocial")
public	ApiResponse<ObraSocial> crearObraSocial (@RequestBody ObraSocial obraSocial){
		ApiResponse <ObraSocial> response = new ApiResponse <>();
		if (obraSocialService.existe(obraSocial.getId())) {
			response.setError("La obra social ya existe");
		}else {
			ObraSocial nuevaObraSocial = obraSocialService.guardar(obraSocial);
			response.setData(nuevaObraSocial);
		}
		return response;
	}

@PutMapping("/obraSocial")
public	ApiResponse<ObraSocial> actualizarObraSocial (@RequestBody ObraSocial obraSocial){
		ApiResponse <ObraSocial> response = new ApiResponse <>();
		if (obraSocialService.existe(obraSocial.getId())) {
			ObraSocial nuevaObraSocial = obraSocialService.guardar(obraSocial);
			response.setData(nuevaObraSocial);
		}else {
			response.setError("La obra social no existe.");
			
		}
		return response;
	}
@DeleteMapping("/obraSocial/{id}")
public	String eliminarObraSocial(@PathVariable("id") Long id) {
		if(obraSocialService.existe(id)) {
			obraSocialService.eliminar(id);
			return "Obra social eliminada con éxito.";
		}else {
			return "El id no existe";
		}
	}
*/

    
    @Autowired
    private IObraSocialService obraSocialService; 
    
    // Obtener todas las Obras Sociales
    @GetMapping("/obraSocial")
    public ResponseEntity<ApiResponse<List<ObraSocial>>> mostrarTodosLasObrasSociales() {
        ApiResponse<List<ObraSocial>> response = new ApiResponse<>();
        List<ObraSocial> lista = obraSocialService.mostrarTodos();

        if (lista.isEmpty()) {
            response.setError("No existe ninguna obra social.");
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);  // 404 Not Found
            throw new ResourceNotFoundException("Excepcion; No existe ninguna obra social.");
        } else {
            response.setData(lista);
            return ResponseEntity.ok(response);  // 200 OK
        }
    }

    // Obtener una Obra Social por ID
    @GetMapping("/obraSocial/{id}")
    public ResponseEntity<ApiResponse<ObraSocial>> mostrarObraSocialPorId(@PathVariable("id") Long id) {
        ApiResponse<ObraSocial> response = new ApiResponse<>();
        ObraSocial obraSocial = obraSocialService.mostrarPorId(id);

        if (obraSocial == null) {
            response.setError("No existe el ID " + id.toString());
          //  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);  // 404 Not Found
            throw new ResourceNotFoundException("Excepcion; No existe el ID.");
        } else {
            response.setData(obraSocial);
            return ResponseEntity.ok(response);  // 200 OK
        }
    }

    // Crear una nueva Obra Social
    @PostMapping("/obraSocial")
    public ResponseEntity<ApiResponse<ObraSocial>> crearObraSocial(@RequestBody ObraSocial obraSocial) {
        ApiResponse<ObraSocial> response = new ApiResponse<>();
        if (obraSocialService.existe(obraSocial.getId())) {
            response.setError("La obra social ya existe.");
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);  // 400 Bad Request
            throw new ResourceNotFoundException("Excepcion; Obra Social ya existe.");
        } else {
            ObraSocial nuevaObraSocial = obraSocialService.guardar(obraSocial);
            response.setData(nuevaObraSocial);
            //return ResponseEntity.status(HttpStatus.CREATED).body(response);  // 201 Created
            throw new ResourceNotFoundException("Excepcion; Obra Social agregada.");
        }
    }

    // Actualizar una Obra Social
    @PutMapping("/obraSocial")
    public ResponseEntity<ApiResponse<ObraSocial>> actualizarObraSocial(@RequestBody ObraSocial obraSocial) {
        ApiResponse<ObraSocial> response = new ApiResponse<>();
        if (obraSocialService.existe(obraSocial.getId())) {
            ObraSocial nuevaObraSocial = obraSocialService.guardar(obraSocial);
            response.setData(nuevaObraSocial);
            return ResponseEntity.ok(response);  // 200 OK
        } else {
            response.setError("La obra social no existe.");
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);  // 404 Not Found
            throw new ResourceNotFoundException("Excepcion; Obra Social no existe.");
        }
    }

    // Eliminar una Obra Social
    @DeleteMapping("/obraSocial/{id}")
    public ResponseEntity<String> eliminarObraSocial(@PathVariable("id") Long id) {
        if (obraSocialService.existe(id)) {
            obraSocialService.eliminar(id);
            return ResponseEntity.ok("Obra social eliminada con éxito.");  // 200 OK
        } else {
           //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El ID no existe.");  // 404 Not Found
           throw new ResourceNotFoundException("Excepcion; El ID no existe.");
        }
    }
}
