package de.arcanerum.server.game.core.world;

import de.arcanerum.server.game.core.buildings.Building;
import de.arcanerum.server.game.core.characters.ArcanerumPlayer;
import de.arcanerum.server.game.events.BuildEvent;
import de.arcanerum.server.game.events.MoveEvent;
import de.arcanerum.server.multiplayer.PlayerDatabase;

import java.util.*;

public class WorldSimulation {
    private World world;
    private List<MoveEvent> moveEvents = new ArrayList<>();
    private List<BuildEvent> buildEvents = new ArrayList<>();
    private int time = 0;

    public WorldSimulation(World world) {
        this.world = world;
    }

    private void tick() throws InterruptedException {
        System.out.print(".");

        tickMoveEvents();
        tickBuildEvents();

        time++;
        if(time >= 24) {
            time = 0;
            tickDay();
        }
    }

    private void tickDay() throws InterruptedException {
        tickDailyBuildingIncome();
        System.out.println("\nDay passed!");
    }

    public void addMoveEvent(MoveEvent moveEvent) {
        moveEvents.add(moveEvent);
        System.out.println("Added player move event from player " + moveEvent.getPlayer().name);
    }
    public void addBuildEvent(BuildEvent buildEvent) {
        buildEvents.add(buildEvent);
    }

    public void startSimulation() {
        Timer timer = new Timer();

        TimerTask repeatedTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    tick();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        timer.scheduleAtFixedRate(repeatedTask, 0, 1000);
    }

    private void tickMoveEvents() throws InterruptedException {
        Iterator<MoveEvent> moveEventIterator = moveEvents.iterator();

        while(moveEventIterator.hasNext()) {
            MoveEvent moveEvent = moveEventIterator.next();
            moveEvent.timeCost--;
            if(moveEvent.timeCost == 0) {
                moveEvent.executeEvent();
                //Maybe move this into the event?
                world.movePlayer(moveEvent.getPlayer(), moveEvent.getDirection());
                System.out.println("Removed player move event from player " + moveEvent.getPlayer().name);
                moveEventIterator.remove();
            }
        }
    }

    private void tickBuildEvents() throws InterruptedException {
        Iterator<BuildEvent> buildEventIterator = buildEvents.iterator();

        while(buildEventIterator.hasNext()) {
            BuildEvent buildEvent = buildEventIterator.next();
            buildEvent.timeCost--;
            if(buildEvent.timeCost == 0) {
                buildEvent.executeEvent();
                buildEventIterator.remove();
            }
        }
    }

    private void tickDailyBuildingIncome() throws InterruptedException {
        for(WorldCell wc : this.world.getWorldCellsFlat()) {
            if(!wc.getBuildings().isEmpty()) {
                for(Building b : wc.getBuildings()) {
                    ArcanerumPlayer ap = PlayerDatabase.getPlayerById(b.ownerID);
                    ap.addGold(b.dailyIncome);
                }
            }
        }
    }

    public boolean areValidCellCoordinates(int x, int y) {
        return x >= 0 && x < this.world.getWidth() && y >= 0 && y < this.world.getHeight();
    }

    public World getWorld() {
        return world;
    }
}
