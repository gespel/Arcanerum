package de.arcanerum.server;

import com.sun.net.httpserver.HttpServer;
import de.arcanerum.server.game.core.ArcanerumPlayer;
import de.arcanerum.server.game.core.World;
import de.arcanerum.server.handlers.MoveHandler;
import de.arcanerum.server.handlers.CharacterHandler;
import de.arcanerum.server.multiplayer.PlayerDatabase;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        PlayerDatabase playerDatabase = new PlayerDatabase();
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        World w = new World(20, 20);
        ArcanerumPlayer player = new ArcanerumPlayer("Sten");

        PlayerDatabase.addPlayer(player);
        w.addPlayerToCell(player, 2, 2);
        w.printMapNumPlayers();

        server.setExecutor(null);
        server.createContext("/", new CharacterHandler(w));
        server.createContext("/move", new MoveHandler(w));
        server.start();
    }
}