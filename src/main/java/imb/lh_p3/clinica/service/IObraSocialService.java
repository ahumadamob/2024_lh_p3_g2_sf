/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package imb.lh_p3.clinica.service;

import java.util.List;

import imb.lh_p3.clinica.entity.ObraSocial;

/**
 *
 * @author Christian
 */

public interface IObraSocialService {
    	public List<ObraSocial>mostrarTodos();
	public ObraSocial mostrarPorId(Long id);
	public ObraSocial guardar(ObraSocial turno);
	public void eliminar(Long id);
	public boolean existe(Long id);
}
