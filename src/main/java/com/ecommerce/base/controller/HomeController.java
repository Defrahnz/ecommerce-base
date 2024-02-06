/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.base.controller;

import com.ecommerce.base.model.Product;
import com.ecommerce.base.service.ProductService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nihil
 */
@Controller
@RequestMapping("/")
public class HomeController {
    private final Logger logger=LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private ProductService productoService;
    @GetMapping("")
    public String home(Model model){
        model.addAttribute("productos", productoService.findAll());
        return "usuario/home";
    }
    @GetMapping("productohome/{id}")
    public String productohome(@PathVariable Integer id,Model model){
        logger.info("Id del producto enviado como parámetro: []",id);
        Product producto=new Product();
        Optional<Product> productoOpt=productoService.get(id);
        producto=productoOpt.get();
        model.addAttribute("producto", producto);
        return "usuario/productohome";
    }
}
