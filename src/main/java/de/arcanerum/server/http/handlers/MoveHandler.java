package de.arcanerum.server.http.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import de.arcanerum.server.game.core.world.WorldSimulation;
import de.arcanerum.server.game.events.MoveEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class MoveHandler implements HttpHandler {
    private WorldSimulation worldSim;

    public MoveHandler(WorldSimulation worldSim) {
        this.worldSim = worldSim;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if("POST".equals(exchange.getRequestMethod())) {
            try {
                handlePost(exchange);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else if("GET".equals(exchange.getRequestMethod())) {
            handleGet(exchange);
        }

    }

    private void handlePost(HttpExchange exchange) throws IOException, InterruptedException {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String requestBody = br.lines().collect(Collectors.joining());

        Gson gson = new Gson();
        MoveRequest request = gson.fromJson(requestBody, MoveRequest.class);
        MoveEvent me = new MoveEvent(request.getPlayerName(), request.getDirection(), worldSim);

        MoveResponse responseObj = new MoveResponse("Moved " + request.getDirection() + ".", "queued");
        String responseJson = gson.toJson(responseObj);

        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, responseJson.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(responseJson.getBytes());
        os.close();
    }

    private void handleGet(HttpExchange exchange) throws IOException {
        MoveResponse response = new MoveResponse("You can go north, south, east or west", null);
    }




    public static class MoveRequest {
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
