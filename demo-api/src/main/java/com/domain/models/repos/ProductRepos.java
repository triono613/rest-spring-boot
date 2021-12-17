package com.domain.models.repos;

import java.util.List;

import javax.websocket.server.PathParam;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepos extends CrudRepository<Product, Long> {

    @Query("SELECT p from Product p where p.name = :name ")    
    public Product findProductByName(@PathParam("name") String name );


    @Query("SELECT p from Product p where p.name like:name")
    public List<Product>    findProductByLike(@PathParam("name") String name);

    @Query("SELECT p from Product p where p.category.id = :categoryId")
    public List<Product>    findProductByCategory(@PathParam("categoryId") Long categoryId);

    @Query("SELECT p from Product p where :supplier MEMBER OF p.suppliers")
    public List<Product>    findProductBySupplier(@PathParam("supplier") Supplier supplier);

}
