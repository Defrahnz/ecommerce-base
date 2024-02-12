/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.base.repository;

import com.ecommerce.base.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nihil
 */@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    @Override
    public User save(User usuario);
    Optional<User> findByEmail(String email);
    
}
