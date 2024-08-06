package de.arcanerum.server.game.core;
//14 - 22 Uhr
//14 - 0 Uhr
//14 - 22 Uhr
//0 - 6 Uhr
//6 - 10:30 Uhr
public class ArcanerumCharacter {
    public String name;
    public String guild;
    public int level;
    public int strength;
    public int dexterity;
    public int constitution;
    public int intelligence;
    private int experience;

    public ArcanerumCharacter(String name) {
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
        }
    }
}
