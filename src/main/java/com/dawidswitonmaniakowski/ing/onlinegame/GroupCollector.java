package com.dawidswitonmaniakowski.ing.onlinegame;

import lombok.AllArgsConstructor;

import java.util.*;

import static java.util.Objects.isNull;

@AllArgsConstructor
class GroupCollector {

    private LinkedList<Clan> clans;
    private int groupCount;

    private final List<List<Clan>> response = new LinkedList<>();
    private final LinkedList<ListWithCountedPlayers> forAnalyze = new LinkedList<>();

    List<List<Clan>> executeProcessing() {

        clans.sort(
                Comparator.comparing(Clan::getPoints)
                        .reversed()
                        .thenComparing(Clan::getNumberOfPlayers)
        );

        while(!clans.isEmpty()) {
            Clan clan = clans.poll();
            placeInResult(clan);
        }
        return response;
    }

    private void placeInResult(Clan clan) {
        List<Clan> forRemove = null;

        for(ListWithCountedPlayers clans : forAnalyze) {
            int countPlayers = clans.getPlayers();
            if(groupCount - countPlayers >= clan.getNumberOfPlayers()){
                clans.add(clan);
                if(countPlayers + clan.getNumberOfPlayers() == groupCount){
                    forRemove = clans;
                    break;
                }
                return;
            }
        }
        if(!isNull(forRemove)){
            forAnalyze.remove(forRemove);
            return;
        }
        addNewGroup(clan);
    }
    private void addNewGroup(Clan clan) {
        ListWithCountedPlayers group = new ListWithCountedPlayers();
        group.add(clan);
        response.add(group);
        if(group.getPlayers() < groupCount) {
            forAnalyze.add(group);
        }
    }
}
