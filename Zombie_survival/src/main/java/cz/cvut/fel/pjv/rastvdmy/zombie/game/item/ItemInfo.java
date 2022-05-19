package cz.cvut.fel.pjv.rastvdmy.zombie.game.item;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.features.Sprite;

import java.util.logging.Logger;

public class ItemInfo extends GetAndSetItemInfo {
    public static final Logger LOGGER = Logger.getGlobal();
    public static final int MAX_STATS = 100;

    public void apply(Sprite sprite) {
        if (getHpRegen() != -1 && getWaterRegen() != -1 && getFoodRegen() != -1) {
            setUsed(true);
        }
        String stat = stats.replace("+", ",");
        String[] stats = stat.split(",");
        int int_value = Integer.parseInt(stats[1]);
        double double_value = Double.parseDouble(stats[1]);
        switch (stats[0]) {
            case "HP: " -> sprite.setHp(statsAddition(sprite.getHp(), sprite.maxHp, int_value));
            case "DMG: " -> sprite.dmg += int_value;
            case "SPEED: " -> sprite.getMovementHandler().speed += double_value;
            case "FOOD: " -> sprite.food = statsAddition(sprite.food, MAX_STATS, int_value);
            case "WATER: " -> sprite.water = statsAddition(sprite.water, MAX_STATS, int_value);
            case "MAX HP: " -> sprite.maxHp += int_value;
        }
        LOGGER.info("Item information has been initialized successfully\n");
    }

    public int statsAddition(int base, int max, int delta) {
        return Math.min(base + delta, max);
    }
}