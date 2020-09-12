package ru.rdude.rpg.game.logic.map;

import java.util.HashSet;
import java.util.Set;

public class GameMap {

    private Cell[][] map;

    public GameMap(int width, int height) {
        map = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                map[x][y] = new Cell(x, y);
            }
        }
    }

    public int getWidth() { return map.length; }
    public int getHeight() { return map[0].length; }

    public Cell cell(int x, int y) {
        return map[x][y];
    }

    public Cell cell(Point point) {
        return map[point.x][point.y];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < map.length; x++) {
            builder.append("\n\r");
            for (int y = 0; y < map[1].length; y++) {
                builder.append(map[x][y]);
            }
        }
        return builder.toString();
    }

    public int nonNullCells(CellProperty.Type cellPropertyType) {
        int realNonNullCells = 0;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                switch (cellPropertyType) {
                    case BIOM:
                        if (map[x][y].getBiom() != null) realNonNullCells++;
                        break;
                    case ROAD:
                        if (map[x][y].getRoad() != null) realNonNullCells++;
                        break;
                }
            }
        }
        return realNonNullCells;
    }
}
