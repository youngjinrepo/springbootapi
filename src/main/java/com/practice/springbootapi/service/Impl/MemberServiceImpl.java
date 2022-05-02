package com.practice.springbootapi.service.Impl;

import com.practice.springbootapi.controller.dtos.AddressDto;
import com.practice.springbootapi.controller.dtos.MemberDto;
import com.practice.springbootapi.controller.dtos.QMemberDto;
import com.practice.springbootapi.model.Address;
import com.practice.springbootapi.model.Member;
import com.practice.springbootapi.model.QAddress;
import com.practice.springbootapi.model.QMember;
import com.practice.springbootapi.repository.MemberRepository;
import com.practice.springbootapi.service.MemberService;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static com.practice.springbootapi.model.QAddress.*;
import static com.practice.springbootapi.model.QMember.*;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    @Override
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public MemberDto findOne(Long id) {
        MemberDto memberDto = queryFactory
                .select(new QMemberDto(
                        member.name, member.email,
                        address.zipcode, address.street, address.detail, address.type
                ))
                .from(member)
                .leftJoin(member.addressList, address)
                .where(
                        address.id.eq(JPAExpressions.select(address.id.min()).from(address))
                )
                .fetchOne();

        return memberDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressDto> findAddresses(Long id) {
        List<Address> addressList = queryFactory
                .selectFrom(address)
                .where(
                        address.member.id.eq(id)
                )
                .fetch();
        List<AddressDto> addressDtoList = addressList.stream().map(o -> new AddressDto(o))
                .collect(Collectors.toList());
        return addressDtoList;
    }

    @Override
    public boolean addAddress(Long id, @NotNull AddressDto addressDto) {
        Member member = em.find(Member.class, id);
        member.addAddressList( new Address(addressDto.getZipcode(), addressDto.getStreet(), addressDto.getDetail(), addressDto.getType()) );
        return true;
    }
}
