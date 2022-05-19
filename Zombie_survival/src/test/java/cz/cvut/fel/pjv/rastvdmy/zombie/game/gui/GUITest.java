package cz.cvut.fel.pjv.rastvdmy.zombie.game.gui;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.launch.Main;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GUITest {
    ImageView image;
    Main main = new Main();
    GUI gui = new GUI(main);
    public String name;

    @Test
    @DisplayName("Testing image existing")
    public void testing_gui_deathScreen_exists() {
        assertNotNull(gui.getDeathImg());
    }

    @Test
    @DisplayName("Testing confirm image")
    public void testing_gui_confirmDeathImage() {
        String path = "/gameFiles/gameElements/quitStage.png";
        assertEquals(path, gui.getDeathImg());
    }

    @BeforeEach
    @DisplayName("@BeforeEach for testing image info")
    public void setImgInfo() {
        image = new ImageView();
    }

    @Test
    @DisplayName("Testing gui fit height info")
    public void testing_setGet_Icon() {
        image.setFitHeight(400);
        assertEquals(image.getFitHeight(), 400);
    }

    @Test
    @DisplayName("Testing gui fit width info")
    public void setImageInfo() {
        image.setFitWidth(800);
        assertEquals(image.getFitWidth(), 800);
    }
}
