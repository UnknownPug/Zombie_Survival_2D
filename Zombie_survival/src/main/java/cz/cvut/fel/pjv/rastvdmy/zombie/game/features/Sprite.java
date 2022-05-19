package cz.cvut.fel.pjv.rastvdmy.zombie.game.features;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.elements.Settings;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.handlers.MovementHandler;
import javafx.scene.shape.Line;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class Sprite {
    public static final Logger LOGGER = Logger.getGlobal();
    public int money, dmg, food, water, maxHp;

    private final MovementHandler movementHandler;
    private final Obstacles obstacles;
    private final Settings settings;
    private boolean isAgr;
    private long time;
    private int hp;

    public MovementHandler getMovementHandler() {
        return movementHandler;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean isAgr() {
        return isAgr;
    }

    public void setAgr(boolean agr) {
        isAgr = agr;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Obstacles getObstacles() {
        return obstacles;
    }

    public Settings getSettings() {
        return settings;
    }

    public Sprite(Settings settings, Obstacles obstacles, double x, double y, String type)
            throws FileNotFoundException {
        this.settings = settings;
        movementHandler = new MovementHandler(settings, obstacles, type);
        setStats(type);
        this.isAgr = false;
        this.obstacles = obstacles;
        if (x == -1 && y == -1) {
            movementHandler.moveHeroTo(
                    settings.getInitScreen().getScreenWidth() / 2,
                    settings.getInitScreen().getScreenHeight() / 2);
        } else {
            movementHandler.moveHeroTo(x, y);
        }
        LOGGER.info("Sprite has been initialized\n");
    }

    public void proximityCheck(Sprite c) {
        double cX = c.movementHandler.getCoordX();
        double cY = c.movementHandler.getCoordY();
        double range = Math.sqrt(
                Math.pow((cX - movementHandler.getCoordX()), 2) + Math.pow((cY - movementHandler.getCoordY()), 2));
        boolean isClose = range < getSettings().getAgrRange();
        boolean isEscaped = range >= getSettings().getEscapedRange();
        boolean isSee = getObstacles().isIntercept(new Line(movementHandler.getCoordX(), movementHandler.getCoordY(),
                c.movementHandler.getCoordX(), c.movementHandler.getCoordY()));
        if (!isAgr && isClose && isSee) {
            isAgr = true;
        } else if (isAgr && isEscaped && !isSee) {
            isAgr = false;
        }
    }

    private void setStats(String type) {

        this.money = getSettings().getMoneyStart();
        this.food = getSettings().getFoodStart();
        this.water = getSettings().getFoodStart();
        switch (type) {
            case "male" -> {
                this.hp = getSettings().getHpStart();
                this.dmg = getSettings().getDmgStart();
                this.movementHandler.speed = getSettings().getSpeedStart();
                LOGGER.info("Player info has been initialized\n");
            }
            case "enemy1" -> {
                this.hp = getSettings().getEnemy1Hp();
                this.dmg = getSettings().getEnemy1Dmg();
                this.movementHandler.speed = getSettings().getEnemy1Speed();
                LOGGER.info("Enemy1 has been initialized\n");
            }
            case "enemy2" -> {
                this.hp = getSettings().getEnemy2Hp();
                this.dmg = getSettings().getEnemy2Dmg();
                this.movementHandler.speed = getSettings().getEnemy2Speed();
                LOGGER.info("Enemy2 has been initialized\n");
            }
            case "enemy3" -> {
                this.hp = getSettings().getEnemy3Hp();
                this.dmg = getSettings().getEnemy3Dmg();
                this.movementHandler.speed = getSettings().getEnemy3Speed();
                LOGGER.info("Enemy3 has been initialized\n");
            }
        }
        this.maxHp = this.hp;
        LOGGER.info("SetStats has been initialized successfully\n");
    }
}
