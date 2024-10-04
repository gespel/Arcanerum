package de.arcanerum.server.game.events;

import de.arcanerum.server.game.core.characters.ArcanerumPlayer;
import de.arcanerum.server.game.core.characters.Enemy;
import de.arcanerum.server.game.core.fight.Fight;

import java.util.Random;

public class RandomFightEvent extends Event {
    private final ArcanerumPlayer player;

    public RandomFightEvent(ArcanerumPlayer player) {
        this.player = player;
    }


    @Override
    EventOutcome executeEvent() throws InterruptedException {
        Fight f = new Fight(player, new Enemy());
        if(f.fight()) {
            this.player.addExperience(100);
            return EventOutcome.FIGHT_WON;
        }
        else {
            return EventOutcome.FIGHT_LOST;
        }

    }
}
