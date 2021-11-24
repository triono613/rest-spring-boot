package com.domain.controller;

import javax.validation.Valid;

import com.domain.models.entities.Product;
import com.domain.services.ProductServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/products")
public class ProductController {
    
@Autowired
    private ProductServices productServices;

    @PostMapping
    public Product create(@Valid @RequestBody Product product, Errors errors ) {
        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                System.err.println(error.getDefaultMessage());
            }
            throw new RuntimeException("Validation Error");
        }
        return productServices.save(product);
    }

    @GetMapping
    public Iterable<Product> findAll(){
        return productServices.findAll();
    }

    @GetMapping("/{id}")
    public Product findOne(@PathVariable("id") Long id){
        return productServices.findOne(id);
    }

    @PutMapping
    public Product update(@RequestBody Product product){
        return productServices.save(product);

    }
}
