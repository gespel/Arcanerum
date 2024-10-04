package de.arcanerum.server.http.responses;

import de.arcanerum.server.game.core.world.World;
import de.arcanerum.server.game.core.world.WorldCell;

import java.util.List;

public class MapResponse {
    public List<List<WorldCell>> cells;

    public MapResponse(World world) {
        this.cells = world.getWorldCells();
    }
}
