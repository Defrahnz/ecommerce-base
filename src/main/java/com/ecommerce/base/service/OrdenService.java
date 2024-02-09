/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.base.service;

import com.ecommerce.base.model.Orden;
import java.util.List;

/**
 *
 * @author nihil
 */
public interface OrdenService {
   List<Orden> findAll();
    Orden save(Orden orden); 
    String generarNumeroOrden();
}
