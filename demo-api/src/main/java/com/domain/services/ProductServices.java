package com.domain.services;

import javax.transaction.Transactional;

import com.domain.models.entities.Product;
import com.domain.models.repos.ProductRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductServices {
    

    @Autowired
    private ProductRepos productRepos;

    public Product save(Product product){
        return productRepos.save(product);
    }
    public Product findOne(Long id){
        return productRepos.findById(id).get();
    }
    public Iterable<Product> findAll(){
        return productRepos.findAll();
    }
    public void removeOne(Long id){
        productRepos.deleteById(id);
    }
}
