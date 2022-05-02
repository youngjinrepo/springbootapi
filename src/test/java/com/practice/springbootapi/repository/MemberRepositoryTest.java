package com.practice.springbootapi.repository;

import com.practice.springbootapi.controller.dtos.AddressDto;
import com.practice.springbootapi.controller.dtos.MemberDto;
import com.practice.springbootapi.model.Address;
import com.practice.springbootapi.model.Member;
import com.practice.springbootapi.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    MemberService memberService;
    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional(readOnly = true)
    public void memberJoin() throws Exception {
        Address address = new Address(
                "111111", "seoul bangbae", "222", "DEFAULT");
        Member member = new Member(
                "문상훈", "cultureLand1234@naver.com", address);

        Long id = memberService.join(member);

        Address address2 = new Address(
                "222222", "seoul bangbae", "222", "COMPANY");
        address2.setMember(member);
        em.persist(address2);

        em.flush();
        em.clear();
        MemberDto one = memberService.findOne(id);

        System.out.println("one = " + one);

        assertThat(one.getName()).isEqualTo(member.getName());
    }

    @Test
    public void addressList() throws Exception {
        //given
        Address address = new Address(
                "111111", "seoul bangbae", "222", "DEFAULT");
        Member member = new Member(
                "문상훈", "cultureLand1234@naver.com", address);

        Long id = memberService.join(member);

        Address address2 = new Address(
                "222222", "seoul bangbae", "222", "COMPANY");
        address2.setMember(member);
        em.persist(address2);

        em.flush();
        em.clear();
        //when
        List<AddressDto> addresses = memberService.findAddresses(id);

        //then
        for (AddressDto addressDto : addresses) {
            System.out.println("addressDto = " + addressDto);
        }
    }
}