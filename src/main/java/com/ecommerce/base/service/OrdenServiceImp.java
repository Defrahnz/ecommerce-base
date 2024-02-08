/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.base.service;

import com.ecommerce.base.model.Orden;
import com.ecommerce.base.repository.OrdenRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nihil
 */
@Service
public class OrdenServiceImp implements OrdenService{
    @Autowired
    private OrdenRepository ordenRepository;
    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }
    public String generadorNumeroOrden(){
        int num=0;
        String numeroConcat="";
        List<Orden> ordenes=findAll();
        List<Integer> numeros=new ArrayList<>();
        ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));
        if(ordenes.isEmpty()){
            num=1;
        }else{
            num=numeros.stream().max(Integer::compare).get();
            num++;
        }
        if(num<10){
            numeroConcat="000000000"+String.valueOf(num);
        }else if(num<100){
            numeroConcat="00000000"+String.valueOf(num);
        }else if(num<1000){
            numeroConcat="0000000"+String.valueOf(num);
        }else if(num<10000){
            numeroConcat="000000"+String.valueOf(num);
        }else if(num<100000){
            numeroConcat="00000"+String.valueOf(num);
        }else if(num<1000000){
            numeroConcat="0000"+String.valueOf(num);
        }else if(num<10000000){
            numeroConcat="000"+String.valueOf(num);
        }else if(num<100000000){
            numeroConcat="00"+String.valueOf(num);
        }else if(num<1000000000){
            numeroConcat="0"+String.valueOf(num);
        }
        return numeroConcat;
    }
}
