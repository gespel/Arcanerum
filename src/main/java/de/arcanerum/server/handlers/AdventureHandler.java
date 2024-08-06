package de.arcanerum.server.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class AdventureHandler implements HttpHandler {
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
        AdventureRequest request = gson.fromJson(requestBody, AdventureRequest.class);
        startAdventure(request);

        AdventureStartResponse responseObj = new AdventureStartResponse("Starting: " + request.getAdventure() + " now.");
        String responseJson = gson.toJson(responseObj);

        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, responseJson.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(responseJson.getBytes());
        os.close();
    }

    private void handleGet(HttpExchange exchange) throws IOException {
        AdventureListResponse responseObj = new AdventureListResponse();
        Gson gson = new Gson();
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        OutputStream os = exchange.getResponseBody();
        os.write(gson.toJson(responseObj).getBytes());
    }


    private void startAdventure(AdventureRequest request) {
        System.out.println("Starting Adventure: " + request.getAdventure());
    }

    static class AdventureRequest {
        private int adventure;

        public int getAdventure() {
            return adventure;
        }

        public void setAdventure(int adventure) {
            this.adventure = adventure;
        }
    }

    static class AdventureStartResponse {
        private String message;

        public AdventureStartResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    static class AdventureListResponse {
        private String message;

        public AdventureListResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
