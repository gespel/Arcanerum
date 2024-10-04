package de.arcanerum.server;

import com.sun.net.httpserver.HttpServer;
import de.arcanerum.server.game.Game;
import de.arcanerum.server.http.handlers.BuildHandler;
import de.arcanerum.server.http.handlers.MapHandler;
import de.arcanerum.server.http.handlers.MoveHandler;
import de.arcanerum.server.http.handlers.CharacterHandler;

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
        server.createContext("/build", new BuildHandler(game.getWorldSimulation()));
        server.start();
    }
}