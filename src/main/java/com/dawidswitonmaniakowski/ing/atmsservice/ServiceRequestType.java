package com.dawidswitonmaniakowski.ing.atmsservice;

public enum ServiceRequestType {
    FAILURE_RESTART(0),
    PRIORITY(1),
    SIGNAL_LOW(2),
    STANDARD(3);

    final int priority;

    ServiceRequestType(int priority) {
        this.priority = priority;
    }

    public int getPriority(){
        return this.priority;
    }
}
