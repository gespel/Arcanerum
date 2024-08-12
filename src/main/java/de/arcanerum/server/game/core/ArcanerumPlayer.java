package de.arcanerum.server.game.core;
//14 - 22 Uhr
//14 - 0 Uhr
//14 - 22 Uhr
//0 - 6 Uhr
//6 - 10:30 Uhr
public class ArcanerumPlayer extends Character {

    public ArcanerumPlayer(String name) {
        this.level = 1;
        this.name = name;
        this.guild = "";
        this.experience = 0;
        this.strength = 10;
        this.dexterity = 10;
        this.constitution = 10;
        this.intelligence = 10;
    }

    public void addExperience(int experience) {
        this.experience += experience;
        checkLevel();
    }

    private void checkLevel() {
        if(this.experience >= (this.level*this.level) + 10) {
            this.experience -= (this.level*this.level) + 10;
            this.level++;
            this.strength += 1;
            this.dexterity += 1;
            this.constitution += 1;
            this.intelligence += 1;
        }
    }
}
