package com.practice.springbootapi.service.Impl;

import com.practice.springbootapi.model.Product;
import com.practice.springbootapi.repository.ProductRepository;
import com.practice.springbootapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final EntityManager em;
    private final ProductRepository productRepository;

    @Override
    public Long AddProduct(Product product) {
        productRepository.save(product);
        return product.getId();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
