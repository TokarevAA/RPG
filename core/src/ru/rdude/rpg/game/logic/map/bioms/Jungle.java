package ru.rdude.rpg.game.logic.map.bioms;

public class Jungle extends Biom {

    private static Jungle instance;

    private Jungle() {
    }

    public static Jungle getInstance() {
        if (instance == null) {
            synchronized (DeadLand.class) {
                if (instance == null) {
                    instance = new Jungle();
                }
            }
        }
        return instance;
    }

    @Override
    public Biom getThisInstance() {
        return getInstance();
    }
}
