package com.eats.mspayments.payments.dtos;

public class ItemsDto {

    private Long id;
    private String name;

    public ItemsDto() {}

    public ItemsDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
