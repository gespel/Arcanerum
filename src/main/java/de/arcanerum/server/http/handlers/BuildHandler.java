package de.arcanerum.server.http.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import de.arcanerum.server.game.core.world.WorldSimulation;
import de.arcanerum.server.game.events.BuildEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class BuildHandler implements HttpHandler {
    private WorldSimulation world;

    public BuildHandler(WorldSimulation simulation) {
        this.world = simulation;
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
        BuildRequest request = gson.fromJson(requestBody, BuildRequest.class);
        BuildEvent be = new BuildEvent(request.getPlayerName(), request.getBuildingName(), world);

        BuildResponse responseObj = new BuildResponse("Building queued!", "queued");
        String responseJson = gson.toJson(responseObj);

        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, responseJson.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(responseJson.getBytes());
        os.close();
    }

    private void handleGet(HttpExchange exchange) throws IOException {
        //TODO: GET BUILDINGS ON CELL
        BuildResponse response = new BuildResponse("Current buildings are:", "queued");
    }

    public static class BuildRequest {
        private String buildingName;
        private String playerName;

        public String getBuildingName() {
            return buildingName;
        }

        public void setBuildingName(String direction) {
            this.buildingName = buildingName;
        }

        public String getPlayerName() {
            return playerName;
        }

        public void setPlayerName(String playerName) {
            this.playerName = playerName;
        }
    }

    static class BuildResponse {
        private String message;
        private String event;

        public BuildResponse(String message, String event) {
            this.message = message;
            this.event = event;
        }
    }
}
