package com.practice.springbootapi.controller.dtos;

import com.practice.springbootapi.model.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {

    private String zipcode;
    private String street;
    private String detail;

    private String type;

    public AddressDto(Address address) {
        zipcode = address.getZipcode();
        street = address.getStreet();
        detail = address.getDetail();
        type = address.getType();
    }
}
