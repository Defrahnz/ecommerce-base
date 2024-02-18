/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.base.service;

import com.ecommerce.base.model.User1;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author nihil
 */
public interface UserService {
    List<User1> findAll();
    Optional<User1> findById(Integer id);
    User1 save(User1 usuario);
    Optional<User1> findByEmail(String email);
}
