package com.practice.springbootapi.model;

import com.practice.springbootapi.model.audit.Worker;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Worker {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "member",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addressList = new ArrayList<>();

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Member(String name, String email, Address address) {
        this.name = name;
        this.email = email;
        addAddressList(address);
    }

    public void addAddressList(Address address) {
        addressList.add(address);
        address.setMember(this);
    }
}
