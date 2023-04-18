package com.dawidswitonmaniakowski.ing.atmsservice;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;

class ServiceRequestTypeComparator implements Comparator<ServiceRequestType>, Serializable {

    @Serial
    private static final long serialVersionUID = 1000L;

    public int compare(ServiceRequestType o1, ServiceRequestType o2) {
        return Integer.compare(o1.getPriority(), o2.getPriority());
    }
}
