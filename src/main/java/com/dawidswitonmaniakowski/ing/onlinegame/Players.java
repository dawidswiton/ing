package com.dawidswitonmaniakowski.ing.onlinegame;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Players {

    @JsonProperty("groupCount")
    @Min(1)
    @Max(1000)
    private int groupCount;

    @JsonProperty("clans")
    @Size(max = 20000)
    @Valid
    private LinkedList<Clan> clans;
}
