package de.arcanerum.server.game.core.characters;

import java.util.Random;
import java.util.UUID;

public class GameCharacter {
    public String name = "";
    public String guild = "";
    public int level = 0;
    public int strength = 0;
    public int dexterity = 0;
    public int constitution = 0;
    public int intelligence = 0;
    public int experience = 0;
    public int tempHP = 0;
    public int gold = 0;
    public String id = UUID.randomUUID().toString();

    public int getHP() {
        return this.constitution * 10;
    }
    public int getMagicDamage() {
        return this.intelligence * 5;
    }
    public int getNormalDamage() {
        return this.strength * 3;
    }
    public int getNormalDamageHit() {
        Random r = new Random();
        return this.getNormalDamage() + r.nextInt(this.strength);
    }
    public void printCurrentHP() {
        System.out.println(name + ": " + tempHP + "/" + getHP() + " HP");
    }
    public void addGold(int gold) {
        this.gold += gold;
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
