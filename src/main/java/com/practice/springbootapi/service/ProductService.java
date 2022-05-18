package com.practice.springbootapi.service;

import com.practice.springbootapi.controller.dtos.ProductDto;
import com.practice.springbootapi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Long AddProduct(Product product);
    ProductDto findById(Long id);

    Page<Product> findAll(Pageable pageable);
}
