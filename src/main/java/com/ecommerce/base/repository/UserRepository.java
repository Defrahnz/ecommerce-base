/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.base.repository;

import com.ecommerce.base.model.User1;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nihil
 */@Repository
public interface UserRepository extends JpaRepository<User1,Integer>{
    @Override
    public User1 save(User1 usuario);
    Optional<User1> findByEmail(String email);
    
}
