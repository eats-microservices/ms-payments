package com.eats.mspayments.payments.dtos;

public class PayWithPix {

    private String uuid;
    private Integer bankCode;

    public PayWithPix(String uuid, Integer bankCode) {
        this.uuid = uuid;
        this.bankCode = bankCode;
    }

    public String getUuid() {
        return uuid;
    }

    public Integer getBankCode() {
        return bankCode;
    }
}
