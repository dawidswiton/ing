package com.dawidswitonmaniakowski.ing.onlinegame;

import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static java.util.Objects.isNull;

@AllArgsConstructor
class GroupCollectorDeprecated {

    private LinkedList<Clan> clans;
    private int groupCount;

    List<List<Clan>> executeProcessing() {
        List<List<Clan>> response = new LinkedList<>();

        clans.sort(
                Comparator.comparing(Clan::getPoints)
                        .reversed()
                        .thenComparing(Clan::getNumberOfPlayers)
        );

        while(!clans.isEmpty()) {
            response.add(findGroup());
        }
        return response;
    }

    private List<Clan> findGroup() {
        LinkedList<Clan> group = new LinkedList<>();
        Clan clan = clans.poll();
        group.add(clan);
        int listCount = clan.getNumberOfPlayers();

        while(isAbleToFindNext(listCount)) {
            Clan c = findNextElement(listCount);
            if(isNull(c)) break;
            group.add(c);
            listCount += c.getNumberOfPlayers();
        }

        return group;
    }

    private Clan findNextElement(int listCount) {
        int limit = this.groupCount - listCount;
        for(int position = 0; position < clans.size(); position++) {
            Clan c = clans.get(position);
            if(c.getNumberOfPlayers() <= limit){
                clans.remove(position);
                return c;
            }
        }
        return null;
    }

    private boolean isAbleToFindNext(int listCount) {
        if(listCount == groupCount){
            return false;
        }
        if(clans.isEmpty()) {
            return false;
        }
        return true;
    }
}
