package de.arcanerum.server.game.core.world;

import de.arcanerum.server.game.events.MoveEvent;

import java.util.*;

public class WorldSimulation {
    private World world;
    private List<MoveEvent> moveEvents = new ArrayList<>();

    public WorldSimulation(World world) {
        this.world = world;
    }

    private void tick() throws InterruptedException {
        System.out.print(".");
        tickMoveEvents();
    }

    public void addMoveEvent(MoveEvent moveEvent) {
        moveEvents.add(moveEvent);
        System.out.println("Added player move event from player " + moveEvent.getPlayer().name);
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
                world.movePlayer(moveEvent.getPlayer(), moveEvent.getDirection());
                System.out.println("Removed player move event from player " + moveEvent.getPlayer().name);
                moveEventIterator.remove();
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
