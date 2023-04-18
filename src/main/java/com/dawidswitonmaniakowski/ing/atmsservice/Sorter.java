package com.dawidswitonmaniakowski.ing.atmsservice;

import java.util.*;
import java.util.stream.Collectors;

class Sorter {

    private final List<Map<Integer, ServiceTask>> regionAtmId = new ArrayList<>();

    private int lastRegion = -1;

    List<ServiceTask> plan(List<ServiceTask> serviceTasks) {
        removeDuplicatesAndPlaceInCache(serviceTasks);
        return sortList();
    }

    private void removeDuplicatesAndPlaceInCache(List<ServiceTask> serviceTasks) {
        for (int i = 0; i < serviceTasks.size(); i++) {
            ServiceTask serviceTask = serviceTasks.get(i);
            serviceTask.setIndex(i);

            Map<Integer, ServiceTask> atmIdTasks = getRegionAtms(serviceTask.getRegion());

            if (atmIdTasks.containsKey(serviceTask.getAtmId())) {
                swapIfHigherPriority(serviceTask, atmIdTasks);
            } else {
                atmIdTasks.put(serviceTask.getAtmId(), serviceTask);
            }
        }
    }

    private void swapIfHigherPriority(ServiceTask serviceTask, Map<Integer, ServiceTask> atmIdTasks) {
        ServiceTask extractedServiceTask = atmIdTasks.get(serviceTask.getAtmId());
        if (extractedServiceTask.getRequestType().compareTo(serviceTask.getRequestType()) > 0) {
            extractedServiceTask.setRequestType(serviceTask.getRequestType());
        }
    }

    private List<ServiceTask> sortList() {
        return regionAtmId.stream()
                .flatMap(x -> x.values().stream()
                        .sorted(Comparator.comparing(
                                ServiceTask::getRequestType,
                                        new ServiceRequestTypeComparator())
                                .thenComparing(ServiceTask::getIndex)
                        ))
                .collect(Collectors.toList());
    }

    private Map<Integer, ServiceTask> getRegionAtms(int region) {
        int regionArrayPosition = region - 1;
        if (regionArrayPosition > lastRegion) {
            expandListTo(region);
        }
        return regionAtmId.get(regionArrayPosition);
    }

    private void expandListTo(int region) {
        for (int i = lastRegion + 1; i < region; i++) {
            regionAtmId.add(new HashMap<>());
        }
        lastRegion = region - 1;
    }
}
