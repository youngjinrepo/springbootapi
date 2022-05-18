package com.practice.springbootapi.controller;

import com.practice.springbootapi.controller.dtos.ProductDto;
import com.practice.springbootapi.model.Product;
import com.practice.springbootapi.service.ProductService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDto> findProduct(@PathVariable Long id) {
        ProductDto result = productService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/products/")
    public ResponseEntity<Page<Product>> findAll(Pageable pageable) {
        Page<Product> products = productService.findAll(pageable);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @PostMapping("/product/")
    public ResponseEntity<Long> addProduct(ProductDto productDto) {
        Long id = productService.AddProduct(new Product(productDto.getName(), productDto.getDesc(), productDto.getCnt()));
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
