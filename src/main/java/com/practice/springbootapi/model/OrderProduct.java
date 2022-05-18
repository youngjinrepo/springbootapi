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

    public static OrderProduct createOrderProduct(Product product, int count) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.product = product;
        orderProduct.product.minusStockQuantity(count);

        return orderProduct;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
