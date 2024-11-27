/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package imb.lh_p3.clinica.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.lh_p3.clinica.entity.ObraSocial;
import imb.lh_p3.clinica.repository.ObraSocialRepository;
import imb.lh_p3.clinica.service.IObraSocialService;

@Service
public class ObraSocialServiceImpl implements IObraSocialService {

    @Autowired
    private ObraSocialRepository repositorio;

    @Override
    public List<ObraSocial> mostrarTodos() {
        return repositorio.findAll();
    }

    @Override
    public ObraSocial mostrarPorId(Long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public ObraSocial guardar(ObraSocial obraSocial) {
        return repositorio.save(obraSocial);
    }

    @Override
    public void eliminar(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        if(id==null){
            return false;
        }else{
            return repositorio.existsById(id);
        }
    }
}
