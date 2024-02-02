/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.base.service;

import com.ecommerce.base.model.Product;
import java.util.Optional;

/**
 *
 * @author nihil
 */
public interface ProductService {
    public Product save(Product producto);
    public Optional<Product> get(Integer id);
    public void update(Product producto);
    public void delete(Integer id);
}
