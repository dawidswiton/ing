package com.dawidswitonmaniakowski.ing.atmsservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
final class ServiceTask {

    @Getter
    @Setter
    @Min(value = 1, message = "Min value is 1")
    @Max(value = 9999, message = "Max value is 9999")
    private int region;

    private ServiceRequestType requestType;

    @Getter
    @Setter
    @Min(value = 1, message = "Min value is 1")
    @Max(value = 9999, message = "Max value is 9999")
    private int atmId;

    @Getter
    @Setter
    @JsonIgnore
    private int index;

    @JsonProperty
    void setRequestType(ServiceRequestType requestType) {
        this.requestType = requestType;
    }

    @JsonIgnore
    ServiceRequestType getRequestType() {
        return requestType;
    }
}
