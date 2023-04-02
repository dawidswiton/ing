package com.dawidswitonmaniakowski.ing.onlinegame;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/onlinegame")
interface EntrancePlanner {
    @PostMapping(
            path = "/calculate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<List<Clan>> plan(@RequestBody Players players);
}
