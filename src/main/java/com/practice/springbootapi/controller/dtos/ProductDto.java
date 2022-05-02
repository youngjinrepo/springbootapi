package com.practice.springbootapi.controller.dtos;

import com.practice.springbootapi.model.Product;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDto {
    private String name;
    private String description;
    private int stockQuantity;

    public ProductDto(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.stockQuantity = product.getStockQuantity();
    }
}
