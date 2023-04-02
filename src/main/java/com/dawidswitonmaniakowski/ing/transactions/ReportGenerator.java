package com.dawidswitonmaniakowski.ing.transactions;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/transactions")
interface ReportGenerator {
    @PostMapping(
            path = "/report",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Account[] generate(@RequestBody Transaction[] transactions);
}
