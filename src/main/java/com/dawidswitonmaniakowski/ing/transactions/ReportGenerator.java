package com.dawidswitonmaniakowski.ing.transactions;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/transactions")
@Validated
interface ReportGenerator {
    @PostMapping(
            path = "/report",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Account[] generate(@Valid @RequestBody @Size(max = 100000) BankTrans[] transactions);
}
