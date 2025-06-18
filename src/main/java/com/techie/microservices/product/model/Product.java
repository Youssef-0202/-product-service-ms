package com.techie.microservices.product.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

/**
 * @author HP
 **/
@Document(value = "product")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Product {
    @Id
    private String id;
    @Indexed(unique = true)
    private String skuCode;
    private String name;
    private String description;
    private BigDecimal price;
}
