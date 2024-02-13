/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.base.controller;

import com.ecommerce.base.model.DetalleOrden;
import com.ecommerce.base.model.Orden;
import com.ecommerce.base.model.Product;
import com.ecommerce.base.model.User;
import com.ecommerce.base.service.DetalleOrdenService;
import com.ecommerce.base.service.OrdenService;
import com.ecommerce.base.service.ProductService;
import com.ecommerce.base.service.UserService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
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
    @Autowired
    private UserService usuarioService;
    @Autowired
    private OrdenService ordenService;
    @Autowired
    private DetalleOrdenService detalleOrdenService;
    @GetMapping("")
    public String home(Model model,HttpSession sesion){
        logger.info("Sesion del usuario: {}",sesion.getAttribute("idusuario"));
        model.addAttribute("productos", productoService.findAll());
        //Sesion
        model.addAttribute("sesion",sesion.getAttribute("idusuario"));
        return "usuario/home";
    }
    @GetMapping("productohome/{id}")
    public String productohome(@PathVariable Integer id,Model model){
        logger.info("Id del producto enviado como parámetro: []",id);
        Product producto;
        Optional<Product> productoOpt=productoService.get(id);
        producto=productoOpt.get();
        model.addAttribute("producto", producto);
        return "usuario/productohome";
    }
    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id,@RequestParam Integer cantidad,Model model){
        DetalleOrden detalleOrden=new DetalleOrden();
        Product producto;
        Double sumaTotal;
        Optional<Product> productoOpt=productoService.get(id);
        logger.info("Producto añadido al carrito: {} ",productoOpt.get());
        logger.info("Cantidad del producto: {}",cantidad);
        producto=productoOpt.get();
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio()* cantidad);
        detalleOrden.setProducto(producto);
        //Validamos que los productos no se añadan 2 veces
        Integer idProducto=producto.getId();
        boolean ingresado=detalles.stream().anyMatch(p -> Objects.equals(p.getProducto().getId(), idProducto));
        if(!ingresado){
           detalles.add(detalleOrden);
        }
        sumaTotal=detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        return "usuario/carrito";
    }
    //Quitar el producto
    @GetMapping("/delete/cart/{id}")
    public String deleteProducto(@PathVariable Integer id,Model model){
        List<DetalleOrden> ordenNuevo=new ArrayList<DetalleOrden>();
        for(DetalleOrden detalleOrden:detalles){
            if(!Objects.equals(detalleOrden.getProducto().getId(), id)){
                ordenNuevo.add(detalleOrden);
            }
        }
        //Nueva lista con los productos que siguieron comprando
        detalles=ordenNuevo;
        double sumaTotal;
        sumaTotal=detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        return "usuario/carrito";
    }
    @GetMapping("/getCart")
    public String getCart(Model model, HttpSession sesion){
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        return "usuario/carrito";
    }
    @GetMapping("/Orden")
    public String order(Model model,HttpSession sesion){
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        //sesion
        model.addAttribute("sesion", sesion.getAttribute("idusuario"));
        return "usuario/resumenorden";
    }    
    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession sesion){
        Date fechaCreacion=new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());
        //Referenciamos al usuario
        User usuario =usuarioService.findById(Integer.parseInt(sesion.getAttribute("idusuario").toString())).get();
        orden.setUsuario(usuario);
        ordenService.save(orden);
        //Guardamos detalles
        for(DetalleOrden dt:detalles){
            dt.setOrden(orden);
            detalleOrdenService.save(dt);
        }
        //Limpiamos valores
        orden=new Orden();
        detalles.clear();
        return "redirect:/";
    }
    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombreProducto,Model model){
        logger.info("Nombre del producto: {}",nombreProducto);
        List<Product> productos=productoService.findAll().stream().filter(p -> p.getNombre().contains(nombreProducto)).collect(Collectors.toList());
        model.addAttribute("productos",productos);
        return "usuario/home";
    }
}
