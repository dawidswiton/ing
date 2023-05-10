package com.dawidswitonmaniakowski.ing.onlinegame;

import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
class EntranceOrganizerWithAnalyzeBuffer implements EntranceOrganizer {

    private LinkedList<Clan> clans;
    private int groupCount;

    private final List<List<Clan>> response = new LinkedList<>();
    private final LinkedList<ListWithCountedPlayers> forAnalyze = new LinkedList<>();

    @Override
    public List<List<Clan>> getOrderedGroups() {
        clans.sort(
                Comparator.comparing(Clan::getPoints)
                        .reversed()
                        .thenComparing(Clan::getNumberOfPlayers)
        );

        while(hasClansForAnalyze()) {
            placeInResponse(clans.poll());
        }
        return response;
    }

    private boolean hasClansForAnalyze() {
        return !clans.isEmpty();
    }

    private void placeInResponse(Clan clan) {
        for(ListWithCountedPlayers entrance : forAnalyze) {
            int countPlayers = entrance.getPlayers();
            if(isClanFitsInNotFullGroup(clan, countPlayers)) {
                entrance.add(clan);
                removeEntranceFromFurtherAnalyzeIfIsFullOfPlayers(entrance);
                return;
            }
        }
        addNewGroup(clan);
    }

    private void removeEntranceFromFurtherAnalyzeIfIsFullOfPlayers(ListWithCountedPlayers entrance) {
        if(entrance.getPlayers() == groupCount) {
            forAnalyze.remove(entrance);
        }
    }

    private boolean isClanFitsInNotFullGroup(Clan clan, int countPlayers) {
        return groupCount - countPlayers >= clan.getNumberOfPlayers();
    }

    private void addNewGroup(Clan clan) {
        ListWithCountedPlayers group = new ListWithCountedPlayers();
        group.add(clan);
        response.add(group);

        if(hasPlaceForNewPlayers(group)) {
            forAnalyze.add(group);
        }
    }

    private boolean hasPlaceForNewPlayers(ListWithCountedPlayers group) {
        return group.getPlayers() < groupCount;
    }
}
