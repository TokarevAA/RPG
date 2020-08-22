package ru.rdude.rpg.game.logic.map.reliefs;

public class Forest extends Relief {
    private static Forest instance;

    private Forest() {
    }

    public static Forest getInstance() {
        if (instance == null) {
            synchronized (Forest.class) {
                if (instance == null) {
                    instance = new Forest();
                }
            }
        }
        return instance;
    }

    @Override
    public Relief getThisInstance() {
        return getInstance();
    }
}
