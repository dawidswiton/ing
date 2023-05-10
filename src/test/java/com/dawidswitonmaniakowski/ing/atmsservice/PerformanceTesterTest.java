package com.dawidswitonmaniakowski.ing.atmsservice;

import com.dawidswitonmaniakowski.ing.Statistics;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Slf4j
class PerformanceTesterTest {

    Random r = new Random();

    @Test
    @Disabled("only performance testing")
    void speedTest() {
        List<Long> times = new ArrayList<>();
        int numberOfEntries = 1000000;

        for (int z = 0; z < 100; z++) {

            List<ServiceTask> serviceTasks = new ArrayList<>();

            for (int i = 0; i < numberOfEntries; i++) {
                serviceTasks.add(
                        new ServiceTask(
                                generateNumber(9999),
                                generateRequestType(),
                                generateNumber(9999),
                                0
                        )
                );
            }

            Instant start = Instant.now();
            //main execution
            OrderSorterImpl sorter = new OrderSorterImpl();
            sorter.buildPlan(serviceTasks);

            Instant finish = Instant.now();
            long timeElapsed = Duration.between(start, finish).toMillis();

            times.add(timeElapsed);
            log.info("numberOfEntries: " + numberOfEntries);
            log.info("time: " + timeElapsed);

        }
        Statistics.statistics(times);
    }

    private int generateNumber(int max) {
        return r.nextInt(max) + 1;
    }

    ServiceRequestType generateRequestType() {
        return ServiceRequestType.values()[generateNumber(3)];
    }

}
