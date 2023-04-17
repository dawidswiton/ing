package com.dawidswitonmaniakowski.ing.onlinegame;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Clan {

    @JsonProperty("numberOfPlayers")
    @Min(1)
    @Max(1000)
    private int numberOfPlayers;

    @JsonProperty("points")
    @Min(1)
    @Max(1000000)
    private int points;
}
