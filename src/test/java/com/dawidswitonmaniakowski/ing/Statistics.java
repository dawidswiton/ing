package com.dawidswitonmaniakowski.ing;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;

@Slf4j
public class Statistics {
    public static void statistics(List<Long> times) {
        long max = times.stream().max(Comparator.naturalOrder()).orElse(0L);
        long min = times.stream().min(Comparator.naturalOrder()).orElse(0L);
        long sum = times.stream().mapToLong(value -> value.longValue()).sum();
        long averageTime = sum / times.size();
        log.info("average time: " + averageTime);
        log.info("min time: " + min);
        log.info("max time: " + max);
    }
}