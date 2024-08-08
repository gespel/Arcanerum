package de.arcanerum.server.game.core;

import java.util.ArrayList;
import java.util.List;

public class WorldCell {
    private int x;
    private int y;
    private List<ArcanerumCharacter> players = new ArrayList<>();

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
    public void addPlayer(ArcanerumCharacter player) {
        players.add(player);
    }
    public void removePlayer(ArcanerumCharacter player) {
        players.remove(player);
    }
    public List<ArcanerumCharacter> getPlayers() {
        return this.players;
    }
}
