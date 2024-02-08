package com.ecommerce.base.service;

import com.ecommerce.base.model.DetalleOrden;
import com.ecommerce.base.repository.DetalleOrdenRepository;
import org.springframework.stereotype.Service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nihil
 */
@Service
public class DetalleOrdenServiceImp implements DetalleOrdenService{
    private DetalleOrdenRepository detalleOrdenRepository;
    @Override
    public DetalleOrden save(DetalleOrden detalleO) {
      return detalleOrdenRepository.save(detalleO);
    }
    
}
