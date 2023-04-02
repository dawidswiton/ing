package com.dawidswitonmaniakowski.ing.onlinegame;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Players {

    @JsonProperty("groupCount")
    private int groupCount;

    @JsonProperty("clans")
    private LinkedList<Clan> clans;
}
