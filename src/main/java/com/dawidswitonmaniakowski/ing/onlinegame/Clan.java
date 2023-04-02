package com.dawidswitonmaniakowski.ing.onlinegame;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Clan {

    @JsonProperty("numberOfPlayers")
    private int numberOfPlayers;

    @JsonProperty("points")
    private int points;
}
