package com.practice.springbootapi.service;

import com.practice.springbootapi.controller.dtos.OrderDto;
import com.practice.springbootapi.controller.dtos.ProductDto;
import com.practice.springbootapi.model.Order;
import com.practice.springbootapi.model.OrderProduct;

import java.util.List;

public interface OrderService {
    Long order(Long memberId, ProductDto productDto);
    OrderDto getOrderById(Long id);
}
