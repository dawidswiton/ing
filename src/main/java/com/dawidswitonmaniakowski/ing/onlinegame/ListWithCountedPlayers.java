package com.dawidswitonmaniakowski.ing.onlinegame;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

class ListWithCountedPlayers extends LinkedList<Clan> implements List<Clan> {

    @Getter
    int players = 0;

    @Override
    public boolean add(Clan o) {
        boolean response = super.add(o);
        players = this.stream().mapToInt(Clan::getNumberOfPlayers).sum();
        return response;
    }
}
