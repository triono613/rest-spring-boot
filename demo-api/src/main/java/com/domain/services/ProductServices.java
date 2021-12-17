package com.domain.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.domain.models.entities.Product;
import com.domain.models.entities.Supplier;
import com.domain.models.repos.ProductRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class ProductServices {
    

    @Autowired
    private ProductRepos productRepos;

    @Autowired
    private SupplierService supplierService;

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

    public void addSupplier(Supplier supplier,Long productId){
        Product product = findOne(productId);
        if( product == null ){
                throw new RuntimeException("product with id :"+ productId +"not found");
        }
        product.getSuppliers().add(supplier);
        save(product);
    }

    public Product findByProductName(String name){
            return productRepos.findProductByName(name);
    }


    public List<Product> findByProductNameLike(String name){
        return productRepos.findProductByLike("%"+name+"%");
    }

    public List<Product> findByCategory(long categoryId){
        return productRepos.findProductByCategory(categoryId);
    }

    public List<Product> findBySupplier(Long supplierId){
        Supplier supplier = supplierService.findOne(supplierId);
        if( supplier == null ){
            return new ArrayList<Product>();
        }
        return productRepos.findProductBySupplier(supplier);
        
    }

}
