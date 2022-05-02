package com.practice.springbootapi.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String zipcode;
    private String street;
    private String detail;

    private String type;

    public Address(String zipcode, String street, String detail, String type) {
        this.zipcode = zipcode;
        this.street = street;
        this.detail = detail;
        this.type = type;
    }

    public String getEntireAddress() {
        return zipcode + " " + street + " " +detail;
    }
}
