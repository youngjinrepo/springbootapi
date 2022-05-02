package com.practice.springbootapi;

import com.practice.springbootapi.model.Address;
import com.practice.springbootapi.model.Member;
import com.practice.springbootapi.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@RequiredArgsConstructor
public class DataInsert {

    private final DataInsertService dataInsertService;

    @PostConstruct
    public void init() {
        dataInsertService.init();
    }

    @Component
    static class DataInsertService {
        @PersistenceContext
        EntityManager em;

        @Transactional
        public void init() {
            Member member = new Member("문상훈", "cultureLand1234@naver.com");
            Address address = new Address("111111", "seoul bangbae", "222", "DEFAULT");
            member.addAddressList(address);
            Address address2 = new Address("222222", "seoul bangbae", "222", "COMPANY");
            member.addAddressList(address2);
            Address address3 = new Address("333333", "seoul seochogu", "222", "HOME");
            member.addAddressList(address3);

            em.persist(member);

            Product product = new Product("chickin", "roasted", 99);
            em.persist(product);
            Product product1 = new Product("pizza", "tomato", 3);
            em.persist(product1);
            Product product2 = new Product("calcium", "dndn", 22);
            em.persist(product2);
        }
    }
}
