/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nihil
 */
@Controller
@RequestMapping("/productos")
public class ProductController {
    
    @GetMapping("")
    public String show(){
       return "products/show";
   } 
    
    @GetMapping("/create")
    public String create(){
        return "products/create";
    }
}
