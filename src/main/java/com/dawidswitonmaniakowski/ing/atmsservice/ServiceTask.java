package com.dawidswitonmaniakowski.ing.atmsservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
final class ServiceTask {

    @Getter
    @Setter
    private int region;

    private ServiceRequestType requestType;

    @Getter
    @Setter
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
