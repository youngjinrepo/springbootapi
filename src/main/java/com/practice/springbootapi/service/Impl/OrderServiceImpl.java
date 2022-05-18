package com.practice.springbootapi.service.Impl;

import com.practice.springbootapi.controller.dtos.OrderDto;
import com.practice.springbootapi.controller.dtos.ProductDto;
import com.practice.springbootapi.controller.dtos.QOrderDto;
import com.practice.springbootapi.exception.ResourceNotFoundException;
import com.practice.springbootapi.model.*;
import com.practice.springbootapi.repository.MemberRepository;
import com.practice.springbootapi.repository.OrderRepository;
import com.practice.springbootapi.repository.ProductRepository;
import com.practice.springbootapi.service.OrderService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static com.practice.springbootapi.model.QMember.*;
import static com.practice.springbootapi.model.QOrder.*;
import static com.practice.springbootapi.model.QOrderProduct.*;
import static com.practice.springbootapi.model.QProduct.*;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final EntityManager em;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final JPAQueryFactory queryFactory;

    @Override
    @Transactional
    public Long order(Long memberId, ProductDto productDto) {

        Member member = memberRepository.findById(memberId).orElseThrow( () -> new ResourceNotFoundException("Order", "id", memberId));

        Product product = productRepository.getById(productDto.getProductId());
        OrderProduct orderProduct = OrderProduct.createOrderProduct(product, productDto.getCnt());
        em.persist(orderProduct);

        Order order = Order.createOrder(member, orderProduct);
        em.persist(order);

        return order.getId();
    }

    @Override
    public OrderDto getOrderById(Long id) {
        return queryFactory
                .select(
                        new QOrderDto(
                                member.name,
                                order.orderDate,
                                order.status
                        )
                )
                .from(member)
                .join(member.orderList, order)
                .where(order.id.eq(id))
                .fetchOne();
    }
}
