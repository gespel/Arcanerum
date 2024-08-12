package de.arcanerum.server.game.core;

import java.util.Random;

public class Enemy extends Character {
    private Random rand = new Random();

    public Enemy() {
        this.level = rand.nextInt(5);
        this.name = "Rat";
        this.strength = rand.nextInt(5) + 1;
        this.dexterity = rand.nextInt(5);
        this.intelligence = rand.nextInt(5);
        this.constitution = 30;
    }
}
