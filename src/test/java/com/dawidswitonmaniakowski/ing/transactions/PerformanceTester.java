package com.dawidswitonmaniakowski.ing.transactions;

import com.dawidswitonmaniakowski.ing.Statistics;
import com.dawidswitonmaniakowski.ing.onlinegame.Clan;
import com.dawidswitonmaniakowski.ing.onlinegame.GroupCollector;
import com.dawidswitonmaniakowski.ing.onlinegame.GroupCollectorCorrect;
import com.dawidswitonmaniakowski.ing.onlinegame.GroupCollectorNoOptimization;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class PerformanceTester {

    Random r = new Random();

    @Test
    @Disabled("only performance testing")
    void speedTest() {
        List<Long> times = new ArrayList<>();

        for (int z = 0; z < 100; z++) {
            LinkedList<Clan> clans = new LinkedList<>();

            int numOfPlayersMax = generateNumber(1000);
            int maxPoints = generateNumber(100000);

            int numOfClans = generateNumber(20000);
            for (int i = 0; i < numOfClans; i++) {
                Clan clan = new Clan(generateNumber(numOfPlayersMax), generateNumber(maxPoints));
                clans.add(clan);
            }

            Instant start = Instant.now();
            GroupCollector collector = new GroupCollector(clans, numOfPlayersMax);
            collector.executeProcessing();
            Instant finish = Instant.now();
            long timeElapsed = Duration.between(start, finish).toMillis();

            times.add(timeElapsed);
            log.info("time: " + timeElapsed);
        }
        Statistics.statistics(times);
    }

    private int generateNumber(int max) {
        return r.nextInt((max - 1) + 1) + 1;
    }
}
