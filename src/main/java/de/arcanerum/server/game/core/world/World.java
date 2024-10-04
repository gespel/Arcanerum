package de.arcanerum.server.game.core.world;

import de.arcanerum.server.game.core.characters.ArcanerumPlayer;
import de.arcanerum.server.http.responses.MapResponse;

import java.util.ArrayList;
import java.util.List;

public class World {
    private List<List<WorldCell>> world;
    private int height;
    private int width;

    public World(int worldWidth, int worldHeight) {
        this.height = worldHeight;
        this.width = worldWidth;
        world = new ArrayList<>();

        for(int i = 0; i < worldWidth; i++) {
            this.world.add(new ArrayList<>());
            for(int j = 0; j < worldHeight; j++) {
                this.world.get(i).add(new WorldCell(i, j, CellType.GRASS));
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

    public MapResponse getMapResponse() {
        return new MapResponse(this);
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

    public boolean movePlayer(ArcanerumPlayer player, String direction) {
        int x = 0, x_new = 0, y = 0, y_new = 0;
        boolean moved = false;
        x = this.getPlayerWorldCell(player).getX();
        y = this.getPlayerWorldCell(player).getY();
        x_new = x;
        y_new = y;
        switch(direction) {
            case "north":
                if(y >= 1) {
                    y_new--;
                    moved = true;
                }
                break;
            case "south":
                if(y <= this.height - 2) {
                    y_new++;
                    moved = true;
                }
                break;
            case "west":
                if(x >= 1) {
                    x_new--;
                    moved = true;
                }
                break;
            case "east":
                if(x <= this.width - 2) {
                    x_new++;
                    moved = true;
                }
                break;
        }
        if(!moved)
            return false;
        this.getCell(x_new, y_new).addPlayer(player);
        this.getCell(x, y).removePlayer(player);
        this.printMapNumPlayers();
        return true;
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public List<List<WorldCell>> getWorldCells() {
        return world;
    }
    public List<WorldCell> getWorldCellsFlat() {
        List<WorldCell> flat = new ArrayList<>();
        for(List<WorldCell> row : this.world) {
            flat.addAll(row);
        }
        return flat;
    }
}
