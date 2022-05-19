package cz.cvut.fel.pjv.rastvdmy.zombie.game.handlers;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.handlers.abstractClass.GetAndSetMoveHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovementHandlerTest extends GetAndSetMoveHandler {

    @Test
    @DisplayName("Testing coordinate x")
    public void testing_movementHandlerXCoord() {
        setCoordX(10);
        assertEquals(getCoordX(), 10);
    }

    @Test
    @DisplayName("Testing coordinate y")
    public void testing_movementHandlerYCoord() {
        setCoordY(10);
        assertEquals(getCoordY(), 10);
    }

    @Test
    @DisplayName("Testing direction x in movementHandler")
    public void testing_movementDirectionXHandler() {
        setDirX(300);
        assertEquals(getDirX(), 300);
    }

    @Test
    @DisplayName("Testing direction y in movementHandler")
    public void testing_movementDirectionYHandler() {
        setDirY(300);
        assertEquals(getDirY(), 300);
    }


}
