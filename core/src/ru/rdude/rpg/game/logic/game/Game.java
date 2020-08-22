package ru.rdude.rpg.game.logic.game;

import ru.rdude.rpg.game.logic.gameStates.GameState;
import ru.rdude.rpg.game.logic.gameStates.Map;
import ru.rdude.rpg.game.logic.time.TimeManager;

public class Game {

    private static Game currentGame;

    private TimeManager timeManager;
    private GameState currentGameState;
    private Map gameMap;

    public TimeManager getTimeManager() {
        return timeManager;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public Map getGameMap() {
        return gameMap;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }
}
