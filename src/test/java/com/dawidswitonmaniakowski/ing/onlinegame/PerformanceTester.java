package com.dawidswitonmaniakowski.ing.onlinegame;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class PerformanceTester {

    Random r = new Random();

    @Test
    void speedTest() {
        int w1 = 0;
        int wo = 0;

        for(int z =0; z < 40; z++){
        LinkedList<Clan> clans = new LinkedList<>();
        LinkedList<Clan> clans2 = new LinkedList<>();
        LinkedList<Clan> clans3 = new LinkedList<>();

        int numOfPlayersMax = 50;
        int maxPoints = 1000;

        int numOfClans = 20000;
        for (int i = 0; i < numOfClans; i++) {
            Clan clan = new Clan(generateNumber(numOfPlayersMax), generateNumber(maxPoints));
            clans.add(clan);
            clans2.add(clan);
            clans3.add(clan);
        }


        int groupCount = numOfPlayersMax;

            Instant start = Instant.now();
            GroupCollector collector = new GroupCollector(clans, groupCount);
            List<List<Clan>> response = collector.executeProcessing();
            Instant finish = Instant.now();
            long timeElapsed = Duration.between(start, finish).toMillis();

        Instant starto = Instant.now();
        GroupCollectorNoOptimization collectoro = new GroupCollectorNoOptimization(clans3, groupCount);
        List<List<Clan>> responseo = collectoro.executeProcessing();
        Instant finisho = Instant.now();
        long timeElapsedo = Duration.between(starto, finisho).toMillis();





        log.info("t1: " + timeElapsed + "to: " + timeElapsedo);

        if(timeElapsed > timeElapsedo) {
            wo++;
        }else if(timeElapsed < timeElapsedo) {
            w1++;
        }
        //assertThat(response).isEqualTo(responseo);
        }
        log.info("w1: " + w1 + " wo: " + wo);
    }

    private int generateNumber(int max) {
        return r.nextInt((max - 1) + 1) + 1;
    }
}
