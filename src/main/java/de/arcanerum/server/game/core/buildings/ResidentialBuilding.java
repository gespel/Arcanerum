package de.arcanerum.server.game.core.buildings;

import de.arcanerum.server.game.core.characters.ArcanerumPlayer;

import java.util.UUID;

public class ResidentialBuilding extends Building {
    public ResidentialBuilding(ArcanerumPlayer owner, int x, int y) {
        this.owner = owner;
        this.buildingType = BuildingType.RESIDENTIAL_BUILDING;
        this.name = "Residential Building";
        this.dailyCost = 0;
        this.dailyIncome = 100;
        this.id = UUID.randomUUID().toString();
        this.locationX = x;
        this.locationY = y;
    }
}
