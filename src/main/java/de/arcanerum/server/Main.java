package de.arcanerum.server;

import com.sun.net.httpserver.HttpServer;
import de.arcanerum.server.handlers.AdventureHandler;
import de.arcanerum.server.handlers.CharacterHandler;
import de.arcanerum.server.multiplayer.PlayerDatabase;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        PlayerDatabase playerDatabase = new PlayerDatabase();
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.setExecutor(null);
        server.createContext("/", new CharacterHandler());
        server.createContext("/adventure", new AdventureHandler());
        server.start();
    }
}