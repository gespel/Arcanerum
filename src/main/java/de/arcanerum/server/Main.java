package de.arcanerum.server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;
import de.arcanerum.server.game.Game;
import de.arcanerum.server.game.core.characters.ArcanerumPlayer;
import de.arcanerum.server.game.core.world.World;
import de.arcanerum.server.game.core.world.WorldSimulation;
import de.arcanerum.server.httphandlers.MapHandler;
import de.arcanerum.server.httphandlers.MoveHandler;
import de.arcanerum.server.httphandlers.CharacterHandler;
import de.arcanerum.server.multiplayer.PlayerDatabase;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8088), 0);
        Game game = new Game();

        server.setExecutor(null);
        server.createContext("/", new CharacterHandler(game.getWorld()));
        server.createContext("/move", new MoveHandler(game.getWorldSimulation()));
        server.createContext("/map", new MapHandler(game.getWorld()));
        server.start();
    }
}