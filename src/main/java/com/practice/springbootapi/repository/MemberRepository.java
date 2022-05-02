package com.practice.springbootapi.repository;

import com.practice.springbootapi.controller.dtos.MemberDto;
import com.practice.springbootapi.model.Address;
import com.practice.springbootapi.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
