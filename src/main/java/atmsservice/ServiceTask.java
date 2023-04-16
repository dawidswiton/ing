package atmsservice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
final class ServiceTask {

    @Getter
    @Setter
    private final int region;

    private ServiceRequestType requestType;

    @Getter
    @Setter
    private final int atmId;

    @Getter
    @Setter
    @JsonIgnore
    private int index = 0;

    @JsonProperty
    void setRequestType(ServiceRequestType requestType) {
        this.requestType = requestType;
    }

    @JsonIgnore
    ServiceRequestType getRequestType() {
        return requestType;
    }
}
