/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.base.controller;

import com.ecommerce.base.model.Product;
import com.ecommerce.base.model.User;
import com.ecommerce.base.service.ProductService;
import com.ecommerce.base.service.UploadFileService;
import java.io.IOException;
import java.util.Optional;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author nihil
 */
@Controller
@RequestMapping("/productos")
public class ProductController {
    private final Logger logger=LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productoService;
    @Autowired
    private UploadFileService upload;
    
    @GetMapping("")
    public String show(Model model){
       model.addAttribute("productos", productoService.findAll());
       return "products/show";
   } 
    
    @GetMapping("/create")
    public String create(){
        return "products/create";
    }
   
    @PostMapping("/save")
    public String save(Product producto, @RequestParam("img") MultipartFile file) throws IOException{
        logger.info("Este es el objeto del producto {}",producto);
        User u=new User(1, "", "", "", "", "", "","");
        producto.setUsuario(u);
        //Subir imagenes
        if(producto.getId()==null){//Cuando se crea un producto
           String nombreImagen=upload.saveImage(file);
           producto.setImagen(nombreImagen);
        }else{
           
        }
        productoService.save(producto);
        return "redirect:/productos";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Product producto=new Product();
        Optional<Product> optProducto=productoService.get(id);
        producto=optProducto.get();
        logger.info("El producto se ha buscado:{}",producto);
        model.addAttribute("producto",producto);
        return "products/edit";
    } 
    
    @PostMapping("/update")
    public String update(Product producto, @RequestParam("img") MultipartFile file) throws IOException{
        Product p= new Product();
        p=productoService.get(producto.getId()).get();
        if(file.isEmpty()){//Se edita el producto pero no cambia la imagen
            
            producto.setImagen(p.getImagen());
        }else{//Cuando se edita la imagen
            if(!p.getImagen().contentEquals("default.jpg")){///Eliminar cuando la imagen no sea por defecto
            upload.deleteImage(p.getNombre());
            }
            String nombreImagen=upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        producto.setUsuario(p.getUsuario());
        productoService.update(producto);
        return "redirect:/productos";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Product p=new Product();
        p=productoService.get(id).get();
        if(!p.getImagen().contentEquals("default.jpg")){///Eliminar cuando la imagen no sea por defecto
            upload.deleteImage(p.getNombre());
        }
        productoService.delete(id);
        return "redirect:/productos";
    }
}
