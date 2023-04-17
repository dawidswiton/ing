package com.dawidswitonmaniakowski.ing.atmsservice;

import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/atms")
@Validated
interface OrderPlanner {

    @PostMapping(
            path = "/calculateOrder",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<ServiceTask> plan(@Valid @RequestBody List<ServiceTask> serviceTasks);
}
