package com.techie.microservices.product.controller;

import com.techie.microservices.product.dto.ProductRequest;
import com.techie.microservices.product.dto.ProductResponse;
import com.techie.microservices.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HP
 **/
@RestController
@RequestMapping("/api/product/")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createProduct(productRequest));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return service.getAllProducts();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll(){
        service.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("All product are deleted !");
    }

    @GetMapping("/{skuCode}")
    public ResponseEntity<ProductResponse> findProductBySkuCode( @PathVariable String skuCode){
        ProductResponse response = service.findBySkuCode(skuCode);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/exist/{skuCode}")
    public ResponseEntity<Boolean> isExistBySkuCode(@PathVariable String skuCode){
        return ResponseEntity.ok(service.isExisteBySkuCode(skuCode));
    }

}
