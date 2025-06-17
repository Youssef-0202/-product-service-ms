package com.techie.microservices.product.dto;

import java.math.BigDecimal;

/**
 * @author HP
 **/
public record ProductRequest(
         String id,
         String name,
         String description,
         BigDecimal price
) {
}
