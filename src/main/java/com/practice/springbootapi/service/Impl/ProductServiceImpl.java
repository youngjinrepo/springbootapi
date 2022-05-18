package com.practice.springbootapi.service.Impl;

import com.practice.springbootapi.controller.dtos.ProductDto;
import com.practice.springbootapi.controller.dtos.QProductDto;
import com.practice.springbootapi.model.Product;
import com.practice.springbootapi.model.QProduct;
import com.practice.springbootapi.repository.ProductRepository;
import com.practice.springbootapi.service.ProductService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.practice.springbootapi.model.QProduct.*;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    public Long AddProduct(Product product) {
        productRepository.save(product);
        return product.getId();
    }

    @Override
    public ProductDto findById(Long id) {
        return queryFactory.select(
                        new QProductDto(product.id, product.name, product.description, product.stockQuantity)
                )
                .from(product)
                .where(product.id.eq(id))
                .fetchOne();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
