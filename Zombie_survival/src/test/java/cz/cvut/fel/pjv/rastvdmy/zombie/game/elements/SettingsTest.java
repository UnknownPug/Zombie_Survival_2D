package cz.cvut.fel.pjv.rastvdmy.zombie.game.elements;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SettingsTest extends GetAndSetSettings {

    String xmlPath = "/gameFiles/settings.xml";

    @Test
    @DisplayName("Testing settings.css path")
    public void testing_settingsFolderPath() {
        assertNotNull(getClass().getResource(xmlPath));
    }

    @Test
    @DisplayName("Testing user HP settings")
    public void testing_userHPStart() {
        setHpStart(100);
        assertEquals(getHpStart(), 100);
    }

    @Test
    @DisplayName("Testing user money settings")
    public void testing_userMoneyStart() {
        setMoneyStart(100);
        assertEquals(getMoneyStart(), 100);
    }

    @Test
    @DisplayName("Testing user speed settings")
    public void testing_userSpeedStart() {
        setSpeedStart(100);
        assertEquals(getSpeedStart(), 100);
    }

    @Test
    @DisplayName("Testing dmg settings")
    public void testing_userDMGStart() {
        setDmgStart(10);
        assertEquals(getDmgStart(), 10);
    }

    @Test
    @DisplayName("Testing water settings")
    public void testing_userWaterStart() {
        setWaterStart(1000);
        assertEquals(getWaterStart(), 1000);
    }

    @Test
    @DisplayName("Testing food settings")
    public void testing_userFoodStart() {
        setFoodStart(120);
        assertEquals(getFoodStart(), 120);
    }
}
