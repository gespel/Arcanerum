package de.arcanerum.server.game.events;

import de.arcanerum.server.game.core.characters.ArcanerumPlayer;
import de.arcanerum.server.game.core.characters.Enemy;
import java.util.Random;

public class RandomFightEvent extends Event {
    private ArcanerumPlayer player;

    public RandomFightEvent(ArcanerumPlayer player) {
        this.player = player;
    }

    public RandomFightEvent fight() throws InterruptedException {
        String out = "";
        Enemy e = new Enemy();
        int gainExp = 0;
        int gold = 0;

        this.player.tempHP = this.player.getHP();
        e.tempHP = e.getHP();
        boolean fighting = true;
        boolean won = false;
        int i = 0;

        while(fighting) {
            fightStep(i, player, e);

            if(this.player.tempHP <= 0) {
                fighting = false;
            }
            else if(e.tempHP <= 0) {
                won = true;
                fighting = false;
            }
            i++;
        }

        if(won) {
            Random r = new Random();
            gainExp = r.nextInt(10) + 10;
            player.addExperience(gainExp);
            out = "Fight won!! Gained " + gainExp + " experience.";
        }
        else {
            out = "You lost!";
        }

        this.text = out;
        this.exp = gainExp;
        this.gold = gold;
        player.tempHP = this.player.getHP();
        return this;
    }

    private void fightStep(int nr, ArcanerumPlayer player, Enemy e) throws InterruptedException {
        Random r = new Random();
        if(nr == 0) {
            if(player.intelligence > e.intelligence) {
                System.out.println("Inflicted " + player.getMagicDamage() + " magic damage to enemy!");
                e.tempHP -= player.getMagicDamage();
                e.printCurrentHP();
            }
            else {
                System.out.println("Inflicted " + e.getMagicDamage() + " magic damage to player!");
                player.tempHP -= e.getMagicDamage();
                player.printCurrentHP();
            }
        }
        else {
            if(nr % 2 == 1) {
                int dmg = player.getNormalDamageHit();
                System.out.println("Player turn:");
                System.out.println(dmg + " damage to enemy!");
                e.tempHP -= dmg;
                e.printCurrentHP();
            }
            else {
                int dmg = e.getNormalDamageHit();
                System.out.println("Enemy turn:");
                System.out.println(dmg + " damage to player!");
                player.tempHP -= dmg;
                player.printCurrentHP();
            }
        }
        //Thread.sleep(200);
    }
}
