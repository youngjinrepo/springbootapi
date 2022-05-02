package com.practice.springbootapi.service;

import com.practice.springbootapi.controller.dtos.AddressDto;
import com.practice.springbootapi.controller.dtos.MemberDto;
import com.practice.springbootapi.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MemberService {
    Long join(Member member);
    MemberDto findOne(Long id);

    List<AddressDto> findAddresses(Long id);
    boolean addAddress(Long id, AddressDto addressDto);
}
