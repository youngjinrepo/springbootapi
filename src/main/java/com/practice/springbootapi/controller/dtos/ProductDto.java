package com.practice.springbootapi.controller.dtos;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDto {
    private Long productId;
    private String name;
    private String desc;
    private int cnt;

    @QueryProjection
    public ProductDto(Long productId, String name, String desc, int cnt) {
        this.productId = productId;
        this.name = name;
        this.desc = desc;
        this.cnt = cnt;
    }
}
