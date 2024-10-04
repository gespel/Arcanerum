package de.arcanerum.server.game.core.world;

import de.arcanerum.server.game.core.buildings.Building;
import de.arcanerum.server.game.core.characters.ArcanerumPlayer;

import java.util.ArrayList;
import java.util.List;

public class WorldCell {
    private List<Building> buildings = new ArrayList<>();
    private int x;
    private int y;
    private List<ArcanerumPlayer> players = new ArrayList<>();
    CellType cellType;

    public WorldCell(int x, int y, CellType type) {
        this.x = x;
        this.y = y;
        this.cellType = type;
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

    public List<Building> getBuildings() {
        return buildings;
    }
    public void addBuilding(Building building) {
        buildings.add(building);
    }
}
