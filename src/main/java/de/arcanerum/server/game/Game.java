package de.arcanerum.server.game;

import com.sun.net.httpserver.HttpServer;
import de.arcanerum.server.game.core.characters.ArcanerumPlayer;
import de.arcanerum.server.game.core.world.World;
import de.arcanerum.server.game.core.world.WorldSimulation;
import de.arcanerum.server.multiplayer.PlayerDatabase;

import java.net.InetSocketAddress;

public class Game {
    World world;
    WorldSimulation worldSimulation;
    ArcanerumPlayer player;
    PlayerDatabase playerDatabase;

    public Game() {
        this.playerDatabase = new PlayerDatabase();

        this.world = new World(20, 20);
        this.worldSimulation = new WorldSimulation(this.world);
        this.worldSimulation.startSimulation();
        this.player = new ArcanerumPlayer("Sten");
        PlayerDatabase.addPlayer(player);
        this.world.addPlayerToCell(player, 2, 2);
        this.world.printMapNumPlayers();
    }

    public World getWorld() {
        return world;
    }
    public WorldSimulation getWorldSimulation() {
        return worldSimulation;
    }
    public ArcanerumPlayer getPlayer() {
        return player;
    }
    public PlayerDatabase getPlayerDatabase() {
        return playerDatabase;
    }
}
