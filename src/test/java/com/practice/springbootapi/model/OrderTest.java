package com.practice.springbootapi.model;

import com.practice.springbootapi.controller.dtos.OrderDto;
import com.practice.springbootapi.controller.dtos.ProductDto;
import com.practice.springbootapi.exception.RequestDeniedException;
import com.practice.springbootapi.model.status.OrderStatus;
import com.practice.springbootapi.service.MemberService;
import com.practice.springbootapi.service.OrderService;
import com.practice.springbootapi.service.ProductService;
import org.apache.tomcat.util.net.jsse.PEMFile;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
    private ProductService productService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private OrderService orderService;

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


    @Test
    @DisplayName("주문 취소 실패 테스트")
    @Rollback(value = true)
    public void OrderCancelFail() throws Exception {
        Product product = new Product("protein", "whey", 100);
        Long productId = productService.AddProduct(product);
        ProductDto productDto = productService.findById(productId);

        Member member = new Member("다이슨","www@aaaaa");
        Long memberId = memberService.join(member);

        ProductDto orderDto = new ProductDto(productDto.getProductId(), productDto.getName(), productDto.getDesc(), 30);

        Long orderId = orderService.order(memberId, orderDto);
        Order order = em.find(Order.class, orderId);
        order.setStatus(OrderStatus.DELIVERY);

        em.flush();
        em.clear();

        assertThrows( RequestDeniedException.class, () -> orderService.orderCancel(orderId) );
    }
    
    @Test
    @DisplayName("주문 취소 성공 테스트")
    public void orderCancelSuccess() throws Exception {
        Product product = new Product("protein", "whey", 100);
        Long productId = productService.AddProduct(product);
        ProductDto productDto = productService.findById(productId);

        Member member = new Member("다이슨","www@aaaaa");
        Long memberId = memberService.join(member);

        ProductDto orderDto = new ProductDto(productDto.getProductId(), productDto.getName(), productDto.getDesc(), 30);

        em.flush();
        em.clear();

        Long orderId = orderService.order(memberId, orderDto);

        em.flush();
        em.clear();

        orderService.orderCancel(orderId);

        em.flush();
        em.clear();

        //
        OrderDto canceledOrderDto = orderService.getOrderById(orderId);
        ProductDto canceledProductDto = productService.findById(productId);

        assertThat(canceledOrderDto.getOrderStatus()).isEqualTo(OrderStatus.CANCEL);
        assertThat(canceledProductDto.getCnt()).isEqualTo(100);
    }
}