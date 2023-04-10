package com.dawidswitonmaniakowski.ing.onlinegame;

import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
class GroupCollectorNoOptimization {

    private LinkedList<Clan> clans;
    private int groupCount;

    private final List<List<Clan>> response = new LinkedList<>();

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

        for(List<Clan> clans : response) {
            int countPlayers = ((ListWithCountedPlayers) clans).getPlayers();
            if(groupCount - countPlayers >= clan.getNumberOfPlayers()){
                clans.add(clan);
                return;
            }
        }
        addNewGroup(clan);
    }
    private void addNewGroup(Clan clan) {
        ListWithCountedPlayers group = new ListWithCountedPlayers();
        group.add(clan);
        response.add(group);
    }
}
