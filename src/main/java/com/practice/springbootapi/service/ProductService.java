package com.practice.springbootapi.service;

import com.practice.springbootapi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    Long AddProduct(Product product);
    Optional<Product> findById(Long id);

    Page<Product> findAll(Pageable pageable);
}
