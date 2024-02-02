/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.base.service;

import com.ecommerce.base.model.Product;
import com.ecommerce.base.repository.ProductRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nihil
 */
@Service
public class ProductServiceImp implements ProductService{
    @Autowired
    private ProductRepository productoRepo;
   
    @Override
    public Product save(Product producto) {
        return productoRepo.save(producto);
    }

    @Override
    public Optional<Product> get(Integer id) {
        return productoRepo.findById(id);
    }

    @Override
    public void update(Product producto) {
        productoRepo.save(producto);
    }

    @Override
    public void delete(Integer id) {
        productoRepo.deleteById(id);
    }
    
}
