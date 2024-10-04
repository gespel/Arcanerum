package de.arcanerum.server.game.core.buildings;

import de.arcanerum.server.game.core.characters.ArcanerumPlayer;

public abstract class Building {
    public int dailyCost;
    public int dailyIncome;
    public String name;
    public String id;
    public String description;
    public BuildingType buildingType;
    public ArcanerumPlayer owner;
    public int locationX, locationY;
    //TODO: Guild ownerGuild;
}
