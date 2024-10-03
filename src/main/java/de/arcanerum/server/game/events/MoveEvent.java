package de.arcanerum.server.game.events;

import java.util.Random;
import de.arcanerum.server.game.core.characters.ArcanerumPlayer;

public class MoveEvent extends Event {
    private ArcanerumPlayer player;
    private String direction;
    public int timeCost = 1;

    public MoveEvent(ArcanerumPlayer player, String direction) {
        this.direction = direction;
        this.player = player;
    }
    public String move() throws InterruptedException {
        //boolean moved = this.world.movePlayer(player, request.getDirection());
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        int z = rand.nextInt(10);
        String out = "";

        if(z >= 6) {
            //FIGHT!
            RandomFightEvent fe = new RandomFightEvent(player);
            out = fe.fight().text;
        }
        else {
            out = "Nothing interessting happend!";
        }
        return out;
    }
    /*public boolean move() throws InterruptedException {

        return true;
    }*/

    public String getDirection() {
        return direction;
    }
    public ArcanerumPlayer getPlayer() {
        return player;
    }
}
