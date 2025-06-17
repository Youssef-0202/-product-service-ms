package com.techie.microservices.product.service;

import com.techie.microservices.product.dto.ProductRequest;
import com.techie.microservices.product.dto.ProductResponse;
import com.techie.microservices.product.exception.ProductNotFoundException;
import com.techie.microservices.product.model.Product;
import com.techie.microservices.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author HP
 **/
@Service
@Slf4j
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .skuCode(productRequest.skuCode())
                .build();

        Product savedProduct = repository.save(product);
        log.info("Product {} is saved", product.getId());

        return mapToProductResponse(savedProduct);
    }

    public List<ProductResponse> createProducts(List<ProductRequest> productRequests) {
        return productRequests.stream()
                .map(this::createProduct)
                .collect(Collectors.toList());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = repository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public boolean isExisteBySkuCode(String skuCode){
        return repository.findBySkuCode(skuCode).isPresent();
    }

    public ProductResponse findBySkuCode(String skuCode){
        Product product = repository.findBySkuCode(skuCode)
                .orElseThrow(() -> new ProductNotFoundException(skuCode));
        return mapToProductResponse(product);
    }

    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(),
                product.getDescription(), product.getPrice() , product.getSkuCode());
    }
}
