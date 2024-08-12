package de.arcanerum.server.game.events;

import java.util.Random;
import de.arcanerum.server.game.core.ArcanerumPlayer;

public class MoveEvent extends Event {
    private ArcanerumPlayer player;

    public MoveEvent(ArcanerumPlayer player) {
        this.player = player;
    }
    public String move() {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        int z = rand.nextInt(10);
        String out = "";

        if(z >= 5) {
            //FIGHT!
            FightEvent fe = new FightEvent(player);
            out = fe.fight().text;
        }
        else {
            out = "Nothing interessting happend!";
        }
        return out;
    }
}
