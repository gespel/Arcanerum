package de.arcanerum.server.multiplayer;

import de.arcanerum.server.game.core.ArcanerumCharacter;

import java.util.ArrayList;
import java.util.List;

public class PlayerDatabase {
    public static List<ArcanerumCharacter> players;

    public PlayerDatabase() {
        players = new ArrayList<ArcanerumCharacter>();
        players.add(new ArcanerumCharacter("Sten"));
    }

    public static ArcanerumCharacter getPlayer(String name) {
        for(ArcanerumCharacter player : players) {
            if(player.name.equals(name)) {
                return player;
            }
        }
        return null;
    }
}
