package com.practice.springbootapi.service.Impl;

import com.practice.springbootapi.controller.dtos.ProductDto;
import com.practice.springbootapi.model.Member;
import com.practice.springbootapi.model.Product;
import com.practice.springbootapi.service.MemberService;
import com.practice.springbootapi.service.OrderService;
import com.practice.springbootapi.service.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private OrderService orderService;

    @Test
    @DisplayName("물건 입력 확인")
    public void addProductTest() throws Exception {
        Product product = new Product("protein", "whey", 100);
        Long productId = productService.AddProduct(product);
        ProductDto productDto = productService.findById(productId);

        assertThat(productDto.getName()).isEqualTo("protein");
    }
    
    @Test
    @DisplayName("재고 수량 증감 확인")
    public void stockQuantityTest() throws Exception {

        Product product = new Product("protein", "whey", 100);
        Long productId = productService.AddProduct(product);
        ProductDto productDto = productService.findById(productId);

        Member member = new Member("다이슨","www@aaaaa");
        Long memberId = memberService.join(member);

        ProductDto orderDto = new ProductDto(productDto.getProductId(), productDto.getName(), productDto.getDesc(), 30);

        Long orderId = orderService.order(memberId, orderDto);

        ProductDto orderedProduct = productService.findById(productDto.getProductId());

        assertThat(orderedProduct.getCnt()).isEqualTo(70);
    }
}