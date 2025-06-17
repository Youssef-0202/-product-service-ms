package com.techie.microservices.product.repository;

import com.techie.microservices.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * @author HP
 **/
public interface ProductRepository extends MongoRepository<Product,String> {
    Optional<Product> findBySkuCode(String skuCode);
}
