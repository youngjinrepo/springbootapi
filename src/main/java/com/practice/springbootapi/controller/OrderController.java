package com.practice.springbootapi.controller;

import com.practice.springbootapi.controller.dtos.OrderDto;
import com.practice.springbootapi.controller.dtos.ProductDto;
import com.practice.springbootapi.repository.OrderRepository;
import com.practice.springbootapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<OrderDto> order(Long memberId, ProductDto productDto) {
        Long orderId = orderService.order(memberId, productDto);
        OrderDto orderDto = orderService.getOrderById(orderId);

        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") Long orderId) {
        OrderDto orderDto = orderService.getOrderById(orderId);

        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @PatchMapping("/order/{id}/")
    public ResponseEntity<OrderDto> orderCancel(OrderDto orderDto) {
        orderService.orderCancel(orderDto.getOrderId());
        OrderDto result = orderService.getOrderById(orderDto.getOrderId());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
