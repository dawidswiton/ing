package com.dawidswitonmaniakowski.ing.atmsservice;

import java.util.Comparator;

class ServiceRequestTypeComparator implements Comparator<ServiceRequestType> {

    public int compare(ServiceRequestType o1, ServiceRequestType o2) {
        return Integer.compare(o1.getPriority(), o2.getPriority());
    }
}
