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

/**
 *
 * @author Christian
 */
@Service
public class ObraSocialServiceImpl implements IObraSocialService {
    @Autowired
    private ObraSocialRepository obraSocialRespository;

    @Override
    public List<ObraSocial> mostrarTodos() {
        return obraSocialRespository.findAll();

    }

    @Override
    public ObraSocial mostrarPorId(Long id) {
        return obraSocialRespository.findById(id).orElse(null);
    }

    @Override
    public ObraSocial guardar(ObraSocial obraSocial) {
        return obraSocialRespository.save(obraSocial);
    }

    @Override
    public void eliminar(Long id) {
        obraSocialRespository.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        return obraSocialRespository.existsById(id);
    }

}
