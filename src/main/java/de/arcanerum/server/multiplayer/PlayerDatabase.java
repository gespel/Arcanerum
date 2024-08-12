package de.arcanerum.server.multiplayer;

import de.arcanerum.server.game.core.ArcanerumPlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayerDatabase {
    public static List<ArcanerumPlayer> players;

    public PlayerDatabase() {
        players = new ArrayList<ArcanerumPlayer>();
    }

    public static ArcanerumPlayer getPlayer(String name) {
        for(ArcanerumPlayer player : players) {
            if(player.name.equals(name)) {
                return player;
            }
        }
        return null;
    }

    public static void addPlayer(ArcanerumPlayer player) {
        players.add(player);
    }
}
