package com.practice.springbootapi.model;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class OrderProduct {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_PRODUCT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
