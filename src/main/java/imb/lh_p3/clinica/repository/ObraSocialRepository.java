/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package imb.lh_p3.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import imb.lh_p3.clinica.entity.ObraSocial;

@Repository
public interface ObraSocialRepository extends JpaRepository<ObraSocial, Long> {

}
