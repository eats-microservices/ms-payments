package com.eats.mspayments.payments.dtos;

public class PayWithCardRequest {

    private Boolean isCredit;
    private String lastFourCardNumbers;
    private String ownerName;

    public PayWithCardRequest(Boolean isCredit, String lastFourCardNumbers, String ownerName) {
        this.isCredit = isCredit;
        this.lastFourCardNumbers = lastFourCardNumbers;
        this.ownerName = ownerName;
    }

    public Boolean getCredit() {
        return isCredit;
    }

    public String getLastFourCardNumbers() {
        return lastFourCardNumbers;
    }

    public String getOwnerName() {
        return ownerName;
    }
}
