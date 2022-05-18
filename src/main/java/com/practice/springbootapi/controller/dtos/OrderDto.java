package com.practice.springbootapi.controller.dtos;

import com.practice.springbootapi.model.status.OrderStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDto {
    private Long orderId;
    private String memberName;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;

    @QueryProjection
    public OrderDto(String memberName, LocalDateTime orderDate, OrderStatus orderStatus) {
        this.memberName = memberName;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }
}
