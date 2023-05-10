package com.dawidswitonmaniakowski.ing.onlinegame;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class PlannerImpl implements EntrancePlanner {
    @Override
    public List<List<Clan>> plan(Players players) {

        EntranceOrganizer entranceOrganizer = new EntranceOrganizerWithAnalyzeBuffer(
                players.getClans(),
                players.getGroupCount()
        );
        return entranceOrganizer.getOrderedGroups();
    }
}
