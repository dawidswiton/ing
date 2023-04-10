package com.dawidswitonmaniakowski.ing.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccountTestHelper {
    @JsonProperty("account")
    private String account;

    @JsonProperty("debitCount")
    private String debitCount;

    @JsonProperty("creditCount")
    private String creditCount;

    @JsonProperty("balance")
    private String balance;
}
