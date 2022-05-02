package com.practice.springbootapi.model;

import com.practice.springbootapi.exception.NotEnoughStockException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {

    @Id @GeneratedValue
    @Column(name = "PRODUCT_ID")
    private Long id;

    private String name;
    private String description;
    private int stockQuantity;

    public Product(String name, String description, int stockQuantity) {
        this.name = name;
        this.description = description;
        this.stockQuantity = stockQuantity;
    }

    public void minusStockQuantity(int cnt) {
        int tmp = stockQuantity - cnt;
        if (tmp < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        stockQuantity = tmp;
    }
    public void plusStockQuantity(int cnt) {
        stockQuantity =+ cnt;
    }

}
