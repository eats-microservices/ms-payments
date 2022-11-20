package com.eats.mspayments.payments.dtos;

import java.util.Set;

public class NewOrderDto {
    private Long id;
    private Double price;
    private String status;
    private Long userId;
    private Set<ItemsDto> items;

    public NewOrderDto() {}

    public NewOrderDto(Long id, Double price, String status, Long userId, Set<ItemsDto> items) {
        this.id = id;
        this.price = price;
        this.status = status;
        this.userId = userId;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public Long getUserId() {
        return userId;
    }

    public Set<ItemsDto> getItems() {
        return items;
    }
}
