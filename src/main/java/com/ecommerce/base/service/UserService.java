/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.base.service;

import com.ecommerce.base.model.User;
import java.util.Optional;

/**
 *
 * @author nihil
 */
public interface UserService {
    Optional<User> findById(Integer id);
    User save(User usuario);
    Optional<User> findByEmail(String email);
}
