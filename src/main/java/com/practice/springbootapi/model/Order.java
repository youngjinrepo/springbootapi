package com.practice.springbootapi.model;

import com.practice.springbootapi.model.audit.WorkDate;
import com.practice.springbootapi.model.status.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "ORDERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends WorkDate {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderList")
    private Member member;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>() ;

    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public static Order createOrder(Member member, OrderProduct... orderProduct) {
        Order order = new Order();

        order.member = member;
        for (OrderProduct product : orderProduct) {
            order.orderProducts.add(product);
            product.setOrder(order);
        }

        order.status = OrderStatus.READY;
        order.orderDate = LocalDateTime.now();

        return order;
    }
}
