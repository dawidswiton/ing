package com.dawidswitonmaniakowski.ing.transactions;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.math.BigDecimal;

class Account {

    @Getter
    private final String account;
    @Getter
    private int debitCount = 0;
    @Getter
    private int creditCount = 0;

    @Getter
    @JsonSerialize(using = AmountSerializer.class)
    private BigDecimal balance = new BigDecimal("0");

    public Account(String account) {
        this.account = account;
    }

    void debit(BigDecimal amount){
        this.debitCount++;
        this.balance = this.balance.subtract(amount);
    }

    void credit(BigDecimal amount) {
        this.creditCount++;
        this.balance = this.balance.add(amount);
    }

}
