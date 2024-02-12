/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.base.controller;

import com.ecommerce.base.model.User;
import com.ecommerce.base.service.UserService;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/usuario")
public class UserController {
    private final Logger logger=LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService usuarioService;
    @GetMapping("/registro")
    public String create(){
        return "usuario/registro";
    }
    @PostMapping("/save")
    public String save(User usuario){
        logger.info("Usuario registro: {}",usuario);
        usuario.setTipo("USER");
        usuarioService.save(usuario);
        return "redirect:/";
    }
    @GetMapping("/login")
    public String login(){
        return "usuario/login";
    }
    @PostMapping("/acceder")
    public String acceder(User usuario,HttpSession sesion){
        logger.info("Accesos por: {}",usuario);
        Optional<User> us=usuarioService.findByEmail(usuario.getEmail());
        //logger.info("Usuario obtenido de la BD: {}",us.get());
        if(us.isPresent()){
            sesion.setAttribute("idusuario", us.get().getId());
            if(us.get().getTipo().equals("ADMIN")){
                return "redirect:/administrador";
            }else{
                return "redirect:/";
            }
        }else{
            logger.info("Usuario no existe");
        }
        return "redirect:/";
    }
}
