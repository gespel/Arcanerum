package de.arcanerum.server.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import de.arcanerum.server.game.core.ArcanerumPlayer;
import de.arcanerum.server.game.core.World;
import de.arcanerum.server.game.events.MoveEvent;
import de.arcanerum.server.multiplayer.PlayerDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class MoveHandler implements HttpHandler {
    private World world;

    public MoveHandler(World world) {
        this.world = world;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if("POST".equals(exchange.getRequestMethod())) {
            handlePost(exchange);
        }
        else if("GET".equals(exchange.getRequestMethod())) {
            handleGet(exchange);
        }

    }

    private void handlePost(HttpExchange exchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String requestBody = br.lines().collect(Collectors.joining());

        Gson gson = new Gson();
        MoveRequest request = gson.fromJson(requestBody, MoveRequest.class);
        String eventText = moveCharacter(request);

        MoveResponse responseObj = new MoveResponse("Moved " + request.getDirection() + ".", eventText);
        String responseJson = gson.toJson(responseObj);

        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, responseJson.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(responseJson.getBytes());
        os.close();
    }

    private void handleGet(HttpExchange exchange) throws IOException {
        /*AdventureListResponse responseObj = new AdventureListResponse();
        Gson gson = new Gson();
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        OutputStream os = exchange.getResponseBody();
        os.write(gson.toJson(responseObj).getBytes());*/
    }


    private String moveCharacter(MoveRequest request) {
        System.out.println("Moving " + request.getPlayerName() + ": " + request.getDirection());
        ArcanerumPlayer player = PlayerDatabase.getPlayer(request.getPlayerName());
        MoveEvent me = new MoveEvent(player);
        this.world.movePlayer(player, request.getDirection());
        return me.move();
    }

    static class MoveRequest {
        private String direction;
        private String playerName;

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public String getPlayerName() {
            return playerName;
        }

        public void setPlayerName(String playerName) {
            this.playerName = playerName;
        }
    }

    static class MoveResponse {
        private String message;
        private String event;

        public MoveResponse(String message, String event) {
            this.message = message;
            this.event = event;
        }
    }
}
