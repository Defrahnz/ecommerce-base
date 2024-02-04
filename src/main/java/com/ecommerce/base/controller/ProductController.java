/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.base.controller;

import com.ecommerce.base.model.Product;
import com.ecommerce.base.model.User;
import com.ecommerce.base.service.ProductService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nihil
 */
@Controller
@RequestMapping("/productos")
public class ProductController {
    private final Logger logger=LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productoService;
    
    @GetMapping("")
    public String show(){
       return "products/show";
   } 
    
    @GetMapping("/create")
    public String create(){
        return "products/create";
    }
   
    @PostMapping("/save")
    public String save(Product producto){
        logger.info("Este es el objeto del producto {}",producto);
        User u=new User(1, "", "", "", "", "", "","");
        producto.setUsuario(u);
        productoService.save(producto);
        return "redirect:/productos";
    }
}
