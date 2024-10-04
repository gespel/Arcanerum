package de.arcanerum.server.game.events;

import de.arcanerum.server.game.core.world.WorldSimulation;

public abstract class Event {
    public String text;
    public int exp;
    public int gold;
    WorldSimulation worldSim;
    abstract EventOutcome executeEvent() throws InterruptedException;
}
