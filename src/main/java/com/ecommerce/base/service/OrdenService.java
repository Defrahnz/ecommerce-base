/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.base.service;

import com.ecommerce.base.model.Orden;
import com.ecommerce.base.model.User1;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author nihil
 */
public interface OrdenService {
    List<Orden> findAll();
    Optional<Orden> findById(Integer id);
    Orden save(Orden orden); 
    String generarNumeroOrden();
    List<Orden>findByUsuario(User1 usuario);

}
