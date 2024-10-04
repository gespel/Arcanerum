package de.arcanerum.server.game.core.fight;

import de.arcanerum.server.game.core.characters.ArcanerumPlayer;
import de.arcanerum.server.game.core.characters.Enemy;
import de.arcanerum.server.game.core.characters.GameCharacter;
import de.arcanerum.server.game.events.EventOutcome;

import java.util.Random;

public class Fight {
    GameCharacter character1, character2;
    public Fight(GameCharacter c1, GameCharacter c2) {
        this.character1 = c1;
        this.character2 = c2;
    }

    private void fightStep(int nr) throws InterruptedException {
        if(nr == 0) {

            if(character1.intelligence > character2.intelligence) {
                System.out.println("Inflicted " + character1.getMagicDamage() + " magic damage to " + character2.name + "!");
                character2.tempHP -= character1.getMagicDamage();
                character2.printCurrentHP();
            }
            else {
                System.out.println("Inflicted " + character2.getMagicDamage() + " magic damage to " + character1.name + "!");
                character1.tempHP -= character2.getMagicDamage();
                character1.printCurrentHP();
            }
        }
        else {
            if(nr % 2 == 1) {
                int dmg = character1.getNormalDamageHit();
                System.out.println("Player turn:");
                System.out.println(dmg + " damage to " + character2.name + "!");
                character2.tempHP -= dmg;
                character2.printCurrentHP();
            }
            else {
                int dmg = character2.getNormalDamageHit();
                System.out.println("Enemy turn:");
                System.out.println(dmg + " damage to " + character1.name + "!");
                character1.tempHP -= dmg;
                character1.printCurrentHP();
            }
        }
    }

    public boolean fight() throws InterruptedException {
        boolean won = false;


        this.character1.tempHP = this.character1.getHP();
        character2.tempHP = character2.getHP();
        boolean fighting = true;
        int i = 0;

        while(fighting) {
            fightStep(i);

            if(this.character1.tempHP <= 0) {
                fighting = false;
            }
            else if(character2.tempHP <= 0) {
                won = true;
                fighting = false;
            }
            i++;
        }

        character1.tempHP = this.character1.getHP();
        character2.tempHP = this.character2.getHP();
        return won;
    }
}
