/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.base.controller;

import com.ecommerce.base.model.Product;
import com.ecommerce.base.model.User;
import com.ecommerce.base.service.ProductService;
import java.util.Optional;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String show(Model model){
       model.addAttribute("productos", productoService.findAll());
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
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Product producto=new Product();
        Optional<Product> optProducto=productoService.get(id);
        producto=optProducto.get();
        logger.info("El producto se ha buscado:{}",producto);
        model.addAttribute("producto",producto);
        return "products/edit";
    } 
    
    @PostMapping("/update")
    public String update(Product producto){
        productoService.update(producto);
        return "redirect:/productos";
    }
}
