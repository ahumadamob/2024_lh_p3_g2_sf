/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package imb.lh_p3.clinica.controller;

import java.util.List;

import imb.lh_p3.clinica.exceptions.ElementeYaExisteException;
import imb.lh_p3.clinica.exceptions.ElementoNoExisteException;
import imb.lh_p3.clinica.util.DTOResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


@Controller("/")
public class ObraSocialController {


    @Autowired
    IObraSocialService service;

    @GetMapping("/obraSocial")
    public ResponseEntity<DTOResponse<List<ObraSocial>>> mostrarTodasLasObrasSociales(){
        List<ObraSocial> lista = service.mostrarTodos();
        if(lista.isEmpty()){
            throw new ElementoNoExisteException("No hay obras sociales registradas");
        }else{
            DTOResponse<List<ObraSocial>> dto = new DTOResponse<>(200, "Lista de Obras Sociales", lista);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
    }

    @GetMapping("/obraSocial/{id}")
    public ResponseEntity<DTOResponse<ObraSocial>> mostrarObraSocialPorId(@PathVariable("id") Long id){
        ObraSocial obraSocial = service.mostrarPorId(id);
        if (obraSocial == null){
            throw new ElementoNoExisteException("La Obra Social con el id: "+ id + " no existe");
        }else{
            DTOResponse<ObraSocial> dto = new DTOResponse<>(200,"ObraSocial: ",obraSocial);
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }
    }
    //Modificar esto para que se fije en el Nombre de la obra social porque no se pasa id cuando creas una nueva
    @PostMapping("/obraSocial")
    public ResponseEntity<DTOResponse<ObraSocial>> guardarObraSocial(@Valid @RequestBody ObraSocial obraSocial){
        if(service.existe(obraSocial.getIdObraSocial())){
            throw new ElementeYaExisteException("Ya hay un registro guardado con este nombre de Obra Social");
        }else{
            DTOResponse<ObraSocial> dto = new DTOResponse<>(201, "Obra Social guardada correctamente", service.guardar(obraSocial));
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        }
    }

    @PutMapping("/obraSocial")
    public ResponseEntity<DTOResponse<ObraSocial>> actualizarObraSocial(@Valid @RequestBody ObraSocial obraSocial){
        if (service.existe(obraSocial.getIdObraSocial())){
            DTOResponse<ObraSocial> dto = new DTOResponse<>(200,"Obra Social actualizada correctamente", service.guardar(obraSocial));
            return ResponseEntity.status(HttpStatus.OK).body(dto);
        }else{
            throw new ElementoNoExisteException("La Obra Social que esta intentando actualizar no existe");
        }
    }

    @DeleteMapping("/obraSocial/{id}")
    public ResponseEntity<DTOResponse<?>> eliminarObraSocial(@PathVariable("id") Long id){
        if (service.existe(id)){
            service.eliminar(id);
            DTOResponse<?> dtoSi = new DTOResponse<>(200, "Obra social eliminada correctamente", null);
            return ResponseEntity.status(HttpStatus.OK).body(dtoSi);
        }else {
            throw new ElementoNoExisteException("La Obra Social a elimiinar no existe");
        }
    }
}
