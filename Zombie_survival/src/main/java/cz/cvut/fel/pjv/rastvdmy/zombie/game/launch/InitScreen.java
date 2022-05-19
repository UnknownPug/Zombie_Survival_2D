package cz.cvut.fel.pjv.rastvdmy.zombie.game.launch;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import java.util.logging.Logger;

public class InitScreen {
    public static final Logger LOGGER = Logger.getGlobal();
    private final double width;
    private final double height;

    public double getScreenWidth() {
        return width;
    }

    public double getScreenHeight() {
        return height;
    }


    public InitScreen() {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getBounds();
        width = bounds.getWidth();
        height = bounds.getHeight();
        LOGGER.info("Screen has been initialized successfully\n");
    }
}