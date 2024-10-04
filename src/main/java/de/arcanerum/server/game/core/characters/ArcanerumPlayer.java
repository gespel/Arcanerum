package de.arcanerum.server.game.core.characters;
//14 - 22 Uhr
//14 - 0 Uhr
//14 - 22 Uhr
//0 - 6 Uhr
//6 - 10:30 Uhr
public class ArcanerumPlayer extends GameCharacter {

    public ArcanerumPlayer(String name) {
        this.level = 1;
        this.name = name;
        this.guild = "";
        this.experience = 0;
        this.strength = 10;
        this.dexterity = 10;
        this.constitution = 10;
        this.intelligence = 10;
        this.gold = 1000;
    }
}
