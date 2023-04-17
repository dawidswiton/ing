package com.dawidswitonmaniakowski.ing.atmsservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AtmTestHelper {
    @JsonProperty("region")
    private String region;

    @JsonProperty("atmId")
    private String atmId;
}
