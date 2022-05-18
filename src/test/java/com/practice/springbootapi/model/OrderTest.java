package com.practice.springbootapi.model;

import com.practice.springbootapi.controller.dtos.OrderDto;
import com.practice.springbootapi.service.OrderService;
import org.apache.tomcat.util.net.jsse.PEMFile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class OrderTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;

    @Test
    public void orderTest() throws Exception {
        //given
        Product chicken = new Product("chicken", "roasted", 99);
        em.persist(chicken);
        Product pizza = new Product("pizza", "tomato", 3);
        em.persist(pizza);

        Member member = new Member("주성훈", "joo", new Address("123", "34", "24", "home"));
        em.persist(member);

        //when
        OrderProduct orderChicken =OrderProduct.createOrderProduct( chicken,10);
        OrderProduct orderPizza =OrderProduct.createOrderProduct( pizza,1);

        em.persist(orderChicken);
        em.persist(orderPizza);

        Order order = Order.createOrder(member, orderChicken, orderPizza);
        em.persist(order);

        //then
        assertThat(order.getOrderProducts().size()).isEqualTo(2);
    }
    
    @Test
    public void getOrder() throws Exception {
        //given
        Product chicken = new Product("chicken", "roasted", 99);
        em.persist(chicken);
        Product pizza = new Product("pizza", "tomato", 3);
        em.persist(pizza);

        Member member = new Member("주성훈", "joo", new Address("123", "34", "24", "home"));
        em.persist(member);

        //when
        OrderProduct orderChicken = OrderProduct.createOrderProduct( chicken,10);
        OrderProduct orderPizza = OrderProduct.createOrderProduct( pizza,1);

        em.persist(orderChicken);
        em.persist(orderPizza);

        Order order = Order.createOrder(member, orderChicken, orderPizza);
        em.persist(order);
        Long orderId = order.getId();
        //then
        OrderDto orderDto = orderService.getOrderById(orderId);

        System.out.println("orderDto = " + orderDto);
    }
}