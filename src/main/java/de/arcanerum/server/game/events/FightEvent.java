package de.arcanerum.server.game.events;

import de.arcanerum.server.game.core.ArcanerumPlayer;
import de.arcanerum.server.game.core.Enemy;
import java.util.Random;

public class FightEvent extends Event {
    private ArcanerumPlayer player;

    public FightEvent(ArcanerumPlayer player) {
        this.player = player;
    }

    public FightEvent fight() {
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
        return this;
    }

    private void fightStep(int nr, ArcanerumPlayer player, Enemy e) {
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
                System.out.println(player.getNormalDamage() + " damage to enemy!");
                e.tempHP -= player.getNormalDamage();
                e.printCurrentHP();
            }
            else {
                System.out.println(e.getNormalDamage() + " damage to player!");
                player.tempHP -= e.getNormalDamage();
                player.printCurrentHP();
            }
        }
    }
}
