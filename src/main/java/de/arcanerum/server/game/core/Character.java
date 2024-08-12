package de.arcanerum.server.game.core;

import java.util.Random;

public class Character {
    public String name = "";
    public String guild = "";
    public int level = 0;
    public int strength = 0;
    public int dexterity = 0;
    public int constitution = 0;
    public int intelligence = 0;
    public int experience = 0;
    public int tempHP = 0;

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
}
