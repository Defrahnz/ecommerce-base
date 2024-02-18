/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.base.controller;

import com.ecommerce.base.model.Orden;
import com.ecommerce.base.model.Product;
import com.ecommerce.base.service.OrdenService;
import com.ecommerce.base.service.ProductService;
import com.ecommerce.base.service.UserService;
import java.util.List;
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
@RequestMapping("/administrador")
public class AdminController {
    private Logger logger=LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private ProductService productoService;
    @Autowired
    private UserService usuarioService;
    @Autowired
    private OrdenService ordenService;
    @GetMapping("")
    public String home(Model model){
        List<Product> productos=productoService.findAll();
        model.addAttribute("productos",productos);
        return "admin/home";
    }
    @GetMapping("/usuarios")
    public String usuarios(Model model){
        model.addAttribute("usuarios",usuarioService.findAll());
        return "admin/usuarios";
    }
    @GetMapping("/ordenes")
    public String ordenes(Model model){
        model.addAttribute("ordenes",ordenService.findAll());
         return "admin/ordenes";
    }
    @GetMapping("/detalle/{id}")
    public String detalle(Model model,@PathVariable Integer id){
        logger.info("Id de la orden: {}",id);
        Orden orden=ordenService.findById(id).get();
        model.addAttribute("detalles",orden.getDetalle());
        return "admin/detalleorden";
    }
}
