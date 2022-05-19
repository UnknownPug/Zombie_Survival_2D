package cz.cvut.fel.pjv.rastvdmy.zombie.game.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemTest extends GetAndSetItemInfo {

    ItemInfo item = new ItemInfo();

    @Test
    @DisplayName("Testing Item stats addition")
    public void testing_statsAddition() {
        int base = 100;
        int max = 200;
        int delta = 0;
        assertEquals(item.statsAddition(base, max, delta), Math.min(base + delta, max));
    }

    @Test
    @DisplayName("Testing name")
    public void testing_nameGetAndSet() {
        setName("Water");
        assertEquals(getName(), "Water");
    }

    @Test
    @DisplayName("Testing description")
    public void testing_descriptionGetAndSet() {
        setDesc("This is a water");
        assertEquals(getDesc(), "This is a water");
    }

    @Test
    @DisplayName("Testing stats")
    public void testing_statsGetAndSet() {
        setStats("Best water ever!");
        assertEquals(getStats(), "Best water ever!");
    }

    @Test
    @DisplayName("Testing is used")
    public void testing_usedGetAndSet() {
        setUsed(true);
        assertTrue(isUsed());
    }
}
