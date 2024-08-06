package de.arcanerum.server.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import de.arcanerum.server.game.core.ArcanerumCharacter;
import de.arcanerum.server.multiplayer.PlayerDatabase;

import java.io.IOException;
import java.io.OutputStream;

public class CharacterHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if("GET".equals(exchange.getRequestMethod())) {
            ArcanerumCharacter ac = PlayerDatabase.getPlayer("Sten");
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            Gson gson = new Gson();
            os.write(gson.toJson(ac).getBytes());
            os.close();
        }
    }
}
