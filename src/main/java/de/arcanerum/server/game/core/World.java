package de.arcanerum.server.game.core;

import java.util.ArrayList;
import java.util.List;

public class World {
    private List<List<WorldCell>> world;

    public World(int worldWidth, int worldHeight) {
        world = new ArrayList<>();
        for(int i = 0; i < worldWidth; i++) {
            this.world.add(new ArrayList<>());
            for(int j = 0; j < worldHeight; j++) {
                this.world.get(i).add(new WorldCell(i, j));
            }
        }
        System.out.println("World height: " + this.world.size());
        System.out.println("World width: " + this.world.getFirst().size());
    }

    public WorldCell getCell(int x, int y) {
        return this.world.get(x).get(y);
    }

    public void addPlayerToCell(ArcanerumPlayer player, int x, int y) {
        this.world.get(x).get(y).addPlayer(player);
    }

    public void printMapNumPlayers() {
        for(int i = 0; i < this.world.size(); i++) {
            for(int j = 0; j < this.world.get(i).size(); j++) {
                System.out.print(this.world.get(j).get(i).getPlayers().size() + " ");
            }
            System.out.println();
        }
    }

    public WorldCell getPlayerWorldCell(ArcanerumPlayer player) {
        for(List<WorldCell> row : this.world) {
            for(WorldCell cell : row) {
                if(cell.getPlayers().contains(player)) {
                    return cell;
                }
            }
        }
        return null;
    }

    public void movePlayer(ArcanerumPlayer player, String direction) {
        int x = 0, x_new = 0, y = 0, y_new = 0;
        x = this.getPlayerWorldCell(player).getX();
        y = this.getPlayerWorldCell(player).getY();
        x_new = x;
        y_new = y;
        switch(direction) {
            case "north":
                y_new--;
                break;
            case "south":
                y_new++;
                break;
            case "west":
                x_new--;
                break;
            case "east":
                x_new++;
                break;
        }
        this.getCell(x_new, y_new).addPlayer(player);
        this.getCell(x, y).removePlayer(player);
        this.printMapNumPlayers();
    }
}
