/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.base.service;

import com.ecommerce.base.model.User1;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author nihil
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService usuarioService;
    @Autowired
    private BCryptPasswordEncoder bCrypt;
    @Autowired
    HttpSession sesion;
//    private final Logger logger = new LoggerFactory(UserDetailServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User1> optUser = usuarioService.findByEmail(username);
        if (optUser.isPresent()) {
            sesion.setAttribute("idusuario", optUser.get().getId());
            User1 usuario = optUser.get();
            return User.builder().username(usuario.getNombre()).password(bCrypt.encode(usuario.getPasword())).roles(usuario.getTipo()).build();
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }

}
