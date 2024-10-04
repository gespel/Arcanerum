package de.arcanerum.server.http.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import de.arcanerum.server.game.core.world.World;

import java.io.IOException;
import java.io.OutputStream;

public class MapHandler implements HttpHandler {
    World world;

    public MapHandler(World world) {
        this.world = world;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if("GET".equals(exchange.getRequestMethod())) {
            Gson gson = new Gson();
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            os.write(gson.toJson(world.getMapResponse()).getBytes());
            os.flush();
            os.close();
        }
    }
}
