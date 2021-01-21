package ru.rdude.rpg.game.logic.data;

import ru.rdude.rpg.game.utils.Pair;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MonsterData extends BeingData {

    private static Map<Long, MonsterData> monsters = new HashMap<>();

    private DropProperties drop;


    public MonsterData(long guid) {
        super(guid);
    }

    @Override
    public boolean hasEntityDependency(long guid) {
        return false;
    }

    @Override
    public void replaceEntityDependency(long oldValue, long newValue) {

    }

    public static void storeMonsters(Collection<MonsterData> collection) {
        collection.forEach(monsterData -> monsters.put(monsterData.getGuid(), monsterData));
    }

    public class DropProperties {

        private long itemData;
        private int amount;
        private double chance;

        public DropProperties(long itemData, int amount, double chance) {
            this.itemData = itemData;
            this.amount = amount;
            this.chance = chance;
        }

        public long getItemData() {
            return itemData;
        }

        public void setItemData(long itemData) {
            this.itemData = itemData;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public double getChance() {
            return chance;
        }

        public void setChance(double chance) {
            this.chance = chance;
        }
    }
}
