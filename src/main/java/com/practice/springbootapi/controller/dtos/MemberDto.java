package com.practice.springbootapi.controller.dtos;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {
    private String name;
    private String email;

    private String zipcode;
    private String street;
    private String detail;
    private String type;

    @QueryProjection
    public MemberDto(String name, String email, String zipcode, String street, String detail, String type) {
        this.name = name;
        this.email = email;
        this.zipcode = zipcode;
        this.street = street;
        this.detail = detail;
        this.type = type;
    }
}