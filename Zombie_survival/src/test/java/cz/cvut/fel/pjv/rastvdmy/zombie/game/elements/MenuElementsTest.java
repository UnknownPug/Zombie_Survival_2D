package cz.cvut.fel.pjv.rastvdmy.zombie.game.elements;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MenuElementsTest {
    Menu menu = new Menu();
    String path = "/gameFiles/music/login.mp3";
    String path_2 = "/gameFiles/gameElements/quitStage.png";
    String path_3 = "/gameFiles/loginScreen/loginScreen.jpg";

    @Test
    @DisplayName("Testing music init path exists")
    public void testing_menuMusicPath_isExists() {
        menu.getMain();
        assertNotNull(menu.getPath());
    }

    @Test
    @DisplayName("Testing music path equals to the original")
    public void testingMenu() {
        assertEquals(path, menu.getPath());
    }

    @Test
    @DisplayName("Testing path is exists")
    public void testing_pathContent_isExists() {
        menu.getMain();
        assertNotNull(menu.getPath2(), menu.getPath3());
    }

    @Test
    @DisplayName("Testing path equals to first program path")
    public void testing_firstPath_withGetter() {
        assertEquals(path_2, menu.getPath2());
    }

    @Test
    @DisplayName("Testing path equals to second program path")
    public void testing_secondPath_withGetter() {
        assertEquals(path_3, menu.getPath3());
    }
}
