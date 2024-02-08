/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.base.controller;

import com.ecommerce.base.model.DetalleOrden;
import com.ecommerce.base.model.Orden;
import com.ecommerce.base.model.Product;
import com.ecommerce.base.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author nihil
 */
@Controller
@RequestMapping("/")
public class HomeController {
    private final Logger logger=LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private ProductService productoService;
    //Almacenar los detalles de la orden
    List<DetalleOrden> detalles=new ArrayList<DetalleOrden>();
    //Datos de la orden
    Orden orden=new Orden();
    @GetMapping("")
    public String home(Model model){
        model.addAttribute("productos", productoService.findAll());
        return "usuario/home";
    }
    @GetMapping("productohome/{id}")
    public String productohome(@PathVariable Integer id,Model model){
        logger.info("Id del producto enviado como parámetro: []",id);
        Product producto=new Product();
        Optional<Product> productoOpt=productoService.get(id);
        producto=productoOpt.get();
        model.addAttribute("producto", producto);
        return "usuario/productohome";
    }
    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id,@RequestParam Integer cantidad,Model model){
        DetalleOrden detalleOrden=new DetalleOrden();
        Product producto=new Product();
        Double sumaTotal=0.0;
        Optional<Product> productoOpt=productoService.get(id);
        logger.info("Producto añadido al carrito: {} ",productoOpt.get());
        logger.info("Cantidad del producto: {}",cantidad);
        producto=productoOpt.get();
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setPrecio(producto.getPrecio()*cantidad);
        detalleOrden.setProducto(producto);
        sumaTotal=detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        return "usuario/carrito";
    }
}
