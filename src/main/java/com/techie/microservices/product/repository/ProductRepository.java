package com.techie.microservices.product.repository;

import com.techie.microservices.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author HP
 **/
public interface ProductRepository extends MongoRepository<Product,String> {
}
