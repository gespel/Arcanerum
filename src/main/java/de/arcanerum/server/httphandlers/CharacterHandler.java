package de.arcanerum.server.httphandlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import de.arcanerum.server.game.core.buildings.Building;
import de.arcanerum.server.game.core.characters.ArcanerumPlayer;
import de.arcanerum.server.game.core.world.World;
import de.arcanerum.server.game.core.world.WorldCell;
import de.arcanerum.server.multiplayer.PlayerDatabase;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class CharacterHandler implements HttpHandler {
    private static World world;
    public CharacterHandler(World world) {
        this.world = world;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if("GET".equals(exchange.getRequestMethod())) {
            System.out.println("Rendering character page");
            ArcanerumPlayer ac = PlayerDatabase.getPlayer("Sten");
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            OutputStream os = exchange.getResponseBody();
            Gson gson = new Gson();

            CharacterResponse cr = new CharacterResponse(ac, world.getPlayerWorldCell(ac).getX(), world.getPlayerWorldCell(ac).getY());

            byte[] resp = gson.toJson(cr).getBytes();
            exchange.sendResponseHeaders(200, resp.length);
            try {
                os.write(resp);
                os.flush();
            } catch (IOException e) {
                System.err.println("Error while writing response: " + e.getMessage());
                e.printStackTrace();
            } finally {
                os.close();
                exchange.close();
            }
        }
    }

    private static class CharacterResponse {
        private ArcanerumPlayer character;
        private int x;
        private int y;
        private List<Building> ownedBuildings = new ArrayList<>();

        public CharacterResponse(ArcanerumPlayer character, int x, int y) {
            this.character = character;
            this.x = x;
            this.y = y;

            for(WorldCell wc : world.getWorldCellsFlat()) {
                for(Building building : wc.getBuildings()) {
                    ownedBuildings.add(building);
                }
            }
        }
    }
}
