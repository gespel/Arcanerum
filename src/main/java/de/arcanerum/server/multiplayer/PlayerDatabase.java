package de.arcanerum.server.multiplayer;

import de.arcanerum.server.game.core.characters.ArcanerumPlayer;

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
        System.out.println("adding player " + player.name);
        players.add(player);
    }

    public static ArcanerumPlayer getPlayerById(String id) {
        for(ArcanerumPlayer player : players) {
            if(player.id.equals(id)) {
                return player;
            }
        }
        return null;
    }
}
