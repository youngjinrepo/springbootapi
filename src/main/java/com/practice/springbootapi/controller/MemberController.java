package com.practice.springbootapi.controller;

import com.practice.springbootapi.controller.dtos.AddressDto;
import com.practice.springbootapi.controller.dtos.MemberDto;
import com.practice.springbootapi.model.Address;
import com.practice.springbootapi.model.Member;
import com.practice.springbootapi.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/{id}")
    public ResponseEntity<MemberDto> getMember(@PathVariable("id") Long id) {
        MemberDto memberDto = memberService.findOne(id);
        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }

    @PostMapping("/member")
    public ResponseEntity<MemberDto> join(MemberDto dto) {
        Member member = new Member( dto.getName(), dto.getEmail(),
                new Address( dto.getZipcode(), dto.getStreet(), dto.getDetail(), dto.getType()) );

        Long id = memberService.join(member);
        MemberDto memberDto = memberService.findOne(id);
        return new ResponseEntity<>(memberDto, HttpStatus.CREATED);
    }

    @GetMapping("/member/{id}/address")
    public ResponseEntity<List<AddressDto>> getMemberAddress(@PathVariable("id") Long id) {
        List<AddressDto> addresses = memberService.findAddresses(id);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @PostMapping("/member/{id}/address")
    public ResponseEntity<List<AddressDto>> addMemberAddress(@PathVariable("id") Long id, AddressDto addressDto) {
        boolean addressCnt = memberService.addAddress(id, addressDto);
        List<AddressDto> addresses = memberService.findAddresses(id);
        return new ResponseEntity<>(addresses, HttpStatus.CREATED);
    }

}
