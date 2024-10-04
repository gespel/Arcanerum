package de.arcanerum.server.game.events;

import de.arcanerum.server.game.core.buildings.Building;
import de.arcanerum.server.game.core.buildings.BuildingType;
import de.arcanerum.server.game.core.buildings.ResidentialBuilding;
import de.arcanerum.server.game.core.characters.ArcanerumPlayer;
import de.arcanerum.server.game.core.world.WorldSimulation;
import de.arcanerum.server.multiplayer.PlayerDatabase;

public class BuildEvent extends Event {
    BuildingType buildingType;
    ArcanerumPlayer owner;
    int buildX, buildY;
    public int timeCost;

    public BuildEvent(String ownerName, String buildingType, WorldSimulation world) {
        this.worldSim = world;
        this.buildingType = BuildingType.RESIDENTIAL_BUILDING;
        this.owner = PlayerDatabase.getPlayer(ownerName);
        this.buildX = worldSim.getWorld().getPlayerWorldCell(owner).getX();
        this.buildY = worldSim.getWorld().getPlayerWorldCell(owner).getY();
        this.timeCost = 10;
        this.worldSim.addBuildEvent(this);
    }

    @Override
    public EventOutcome executeEvent() throws InterruptedException {
        ResidentialBuilding rb = new ResidentialBuilding(owner, buildX, buildY);
        worldSim.getWorld().getCell(this.buildX, this.buildY).addBuilding(rb);
        System.out.println("Building " + buildingType + " at " + buildX + ", " + buildY + " built!");
        return null;
    }
}
