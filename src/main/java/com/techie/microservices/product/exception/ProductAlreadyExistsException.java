package com.techie.microservices.product.exception;

/**
 * @author HP
 **/
public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException(String skuCode){
        super("Product With skuCode :"+skuCode+" is Already exists !");
    }
}
