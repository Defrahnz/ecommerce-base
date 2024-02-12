/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.base.service;

import com.ecommerce.base.model.User;
import com.ecommerce.base.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nihil
 */
@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository usuarioRepository;

    @Override
    public Optional<User> findById(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public User save(User usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    
}
