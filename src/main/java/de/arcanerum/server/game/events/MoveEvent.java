package de.arcanerum.server.game.events;

import java.util.Random;
import de.arcanerum.server.game.core.characters.ArcanerumPlayer;
import de.arcanerum.server.game.core.world.WorldSimulation;
import de.arcanerum.server.multiplayer.PlayerDatabase;

public class MoveEvent extends Event {
    private ArcanerumPlayer player;
    private String direction;
    public int timeCost = 1;


    public MoveEvent(String playerName, String direction, WorldSimulation worldSim) throws InterruptedException {
        this.worldSim = worldSim;
        this.player = PlayerDatabase.getPlayer(playerName);
        this.direction = direction;

        int xAdd = 0;
        int yAdd = 0;
        switch (direction) {
            case "north" -> yAdd = -1;
            case "south" -> yAdd = 1;
            case "east" -> xAdd = 1;
            case "west" -> xAdd = -1;
        }
        boolean moved = false;
        if(worldSim.areValidCellCoordinates(
                worldSim.getWorld().getPlayerWorldCell(player).getX()+xAdd,
                worldSim.getWorld().getPlayerWorldCell(player).getY()+yAdd
        )) {
            worldSim.addMoveEvent(this);
            moved = true;
        }
    }
    @Override
    public EventOutcome executeEvent() throws InterruptedException {
        //boolean moved = this.world.movePlayer(player, request.getDirection());
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        int z = rand.nextInt(10);
        EventOutcome outcome = null;

        if(z >= 6) {
            //FIGHT!
            RandomFightEvent fe = new RandomFightEvent(player);

            if(fe.executeEvent() == EventOutcome.FIGHT_WON) {
                outcome = EventOutcome.FIGHT_WON;
            }
            else {
                outcome = EventOutcome.FIGHT_LOST;
            }
        }

        return outcome;
    }

    public String getDirection() {
        return direction;
    }
    public ArcanerumPlayer getPlayer() {
        return player;
    }
}
