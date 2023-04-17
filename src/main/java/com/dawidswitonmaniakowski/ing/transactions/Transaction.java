package com.dawidswitonmaniakowski.ing.transactions;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Transaction {

    @JsonProperty("debitAccount")
    @Size(min = 26, max = 26)
    private String debitAccount;

    @Size(min = 26, max = 26)
    @JsonProperty("creditAccount")
    private String creditAccount;

    @JsonProperty("amount")
    @JsonDeserialize(using = AmountDeserializer.class)
    private BigDecimal amount;

}

