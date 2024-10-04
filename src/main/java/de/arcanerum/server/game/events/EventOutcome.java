package de.arcanerum.server.game.events;

public enum EventOutcome {
    FIGHT_WON, FIGHT_LOST, NOTHING, CITY_ENTERED, CITY_WALKING;

    public String toString() {
        return switch (this) {
            case FIGHT_WON -> "Fight Won";
            case FIGHT_LOST -> "Fight Lost";
            case NOTHING -> "Nothing";
            case CITY_ENTERED -> "City Entered";
            case CITY_WALKING -> "City Walking";
            default -> "Unknown";
        };
    }
}
