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
    PlayerDatabase playerDatabase;

    public Game() {
        this.playerDatabase = new PlayerDatabase();
        this.world = new World(50, 50);
        this.worldSimulation = new WorldSimulation(this.world);
        this.worldSimulation.startSimulation();

        ArcanerumPlayer tester = new ArcanerumPlayer("Sten");
        PlayerDatabase.addPlayer(tester);
        this.world.addPlayerToCell(tester, 2, 2);
    }

    public World getWorld() {
        return world;
    }
    public WorldSimulation getWorldSimulation() {
        return worldSimulation;
    }
    public PlayerDatabase getPlayerDatabase() {
        return playerDatabase;
    }
}
