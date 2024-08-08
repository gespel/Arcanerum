package de.arcanerum.server.handlers;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import de.arcanerum.server.game.core.ArcanerumCharacter;
import de.arcanerum.server.game.core.World;
import de.arcanerum.server.multiplayer.PlayerDatabase;

import java.io.IOException;
import java.io.OutputStream;

public class CharacterHandler implements HttpHandler {
    private World world;
    public CharacterHandler(World world) {
        this.world = world;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if("GET".equals(exchange.getRequestMethod())) {
            ArcanerumCharacter ac = PlayerDatabase.getPlayer("Sten");
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, 0);
            OutputStream os = exchange.getResponseBody();
            Gson gson = new Gson();
            CharacterResponse cr = new CharacterResponse(ac, world.getPlayerWorldCell(ac).getX(), world.getPlayerWorldCell(ac).getY());
            os.write(gson.toJson(cr).getBytes());
            os.close();
        }
    }

    private static class CharacterResponse {
        private ArcanerumCharacter character;
        private int x;
        private int y;

        public CharacterResponse(ArcanerumCharacter character, int x, int y) {
            this.character = character;
            this.x = x;
            this.y = y;
        }

        public void setCharacter(ArcanerumCharacter character) {
            this.character = character;
        }
        public void setX(int x) {
            this.x = x;
        }
        public void setY(int y) {
            this.y = y;
        }

        public ArcanerumCharacter getCharacter() {
            return character;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
    }
}
