package com.domain.models.repos;

import com.domain.models.entities.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepos extends CrudRepository<Product, Long> {

    
    

}
