/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.base.repository;

import com.ecommerce.base.model.Orden;
import com.ecommerce.base.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nihil
 */
@Repository
public interface OrdenRepository extends JpaRepository<Orden,Integer>{
   List<Orden>findByUsuario(User usuario);
}
