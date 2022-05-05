package com.practice.springbootapi.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = true)
class OrderTest {

    @Autowired
    EntityManager em;

    @Test
    public void orderTest() throws Exception {
        //given
        Product product = new Product("chickin", "roasted", 99);
        em.persist(product);
        Product product1 = new Product("pizza", "tomato", 3);
        em.persist(product1);

        //when


        //then
    }
}