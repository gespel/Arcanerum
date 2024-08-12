package de.arcanerum.server.game.core;

import java.util.ArrayList;
import java.util.List;

enum CellType {
    GRASS, FORREST, MOUNTAIN, WATER
}

public class WorldCell {
    private int x;
    private int y;
    private List<ArcanerumPlayer> players = new ArrayList<>();

    public WorldCell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void addPlayer(ArcanerumPlayer player) {
        players.add(player);
    }
    public void removePlayer(ArcanerumPlayer player) {
        players.remove(player);
    }
    public List<ArcanerumPlayer> getPlayers() {
        return this.players;
    }
}
