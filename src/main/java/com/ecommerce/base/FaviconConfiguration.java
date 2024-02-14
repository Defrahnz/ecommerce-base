/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.base;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

/**
 *
 * @author nihil
 */
@Configuration
public class FaviconConfiguration {
    @Bean
    public SimpleUrlHandlerMapping customFaviconHandlerMapping(){
        SimpleUrlHandlerMapping mapping= new SimpleUrlHandlerMapping();
        mapping.setOrder(Integer.MIN_VALUE);
        mapping.setUrlMap(Collections.singletonMap("/static/favicon.ico",faviconRequestHandler()));
        return mapping;
    }
    @Bean
    protected ResourceHttpRequestHandler faviconRequestHandler(){
        ResourceHttpRequestHandler requestHandler=new ResourceHttpRequestHandler();
        requestHandler.setLocations(Collections.singletonList(new ClassPathResource("/")));
        return requestHandler;
    }
}
