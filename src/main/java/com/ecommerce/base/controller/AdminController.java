/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.base.controller;

import com.ecommerce.base.model.Product;
import com.ecommerce.base.service.ProductService;
import com.ecommerce.base.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nihil
 */
@Controller
@RequestMapping("/administrador")
public class AdminController {
    @Autowired
    private ProductService productoService;
    @Autowired
    private UserService usuarioService;
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
}
