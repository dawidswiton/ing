package com.dawidswitonmaniakowski.ing.onlinegame;

import com.dawidswitonmaniakowski.ing.Statistics;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class PerformanceTester {

    Random r = new Random();

    @Test
    void checkCorrectness() {
        for (int z = 0; z < 10; z++) {
            LinkedList<Clan> clans = new LinkedList<>();
            LinkedList<Clan> clans2 = new LinkedList<>();

            int numOfPlayersMax = 100;
            int maxPoints = 1000;

            int numOfClans = 1000;
            for (int i = 0; i < numOfClans; i++) {
                Clan c = new Clan(generateNumber(numOfPlayersMax), generateNumber(maxPoints));
                clans.add(c);
                clans2.add(c);
            }

            GroupCollector collector = new GroupCollector(clans, numOfPlayersMax);
            List<List<Clan>> lists = collector.executeProcessing();

            GroupCollectorCorrect correct = new GroupCollectorCorrect(clans2, numOfPlayersMax);
            List<List<Clan>> expected = correct.executeProcessing();

            assertThat(lists).isEqualTo(expected);

        }

    }

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

    @Test
    @Disabled("only performance testing")
    void speedTest2() {
        List<Long> times = new ArrayList<>();

        for (int z = 0; z < 40; z++) {
            LinkedList<Clan> clans = new LinkedList<>();

            int numOfPlayersMax = 1000;
            int maxPoints = 100000;

            int numOfClans = 20000;
            for (int i = 0; i < numOfClans; i++) {
                Clan clan = new Clan(generateNumber(numOfPlayersMax), generateNumber(maxPoints));
                clans.add(clan);
            }

            Instant start = Instant.now();
            GroupCollectorNoOptimization collector = new GroupCollectorNoOptimization(clans, numOfPlayersMax);
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
