package cz.cvut.fel.pjv.rastvdmy.zombie.game.handlers;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.elements.Settings;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.features.Obstacles;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.features.Sprite;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.logging.Logger;

public class EnemyHandler {
    public static final Logger LOGGER = Logger.getGlobal();
    public static final int ENEMIES_NUMBER = 10;
    public static final int COORD_LIMIT = 40;

    private final Sprite[] enemies;
    private final Settings settings;
    private final Obstacles obstacles;
    private static Sprite enemy;

    public Sprite[] getEnemies() {
        return enemies;
    }

    public EnemyHandler(Settings settings, Obstacles obstacles) throws FileNotFoundException {
        this.settings = settings;
        this.obstacles = obstacles;
        this.enemies = generateEnemies();
    }

    private Sprite[] generateEnemies() throws FileNotFoundException {
        Sprite[] enemies = new Sprite[ENEMIES_NUMBER];
        for (int i = 0; i < ENEMIES_NUMBER; i++) {
            Sprite enemy = randomEnemy(settings, obstacles);
            enemy.getMovementHandler().setEnemyId();
            enemies[i] = enemy;
        }
        LOGGER.info("Enemies has been initialized\n");
        return enemies;
    }

    private static double random(double rightLimit) {
        LOGGER.info("Random has been initialized\n");
        return (double) 40 + new Random().nextDouble() * (rightLimit - (double) COORD_LIMIT);
    }

    private static double[] randomCoords(double w, double h, Settings s, Obstacles ob) throws FileNotFoundException {
        double x, y;
        do {
            x = random(s.getInitScreen().getScreenWidth() - COORD_LIMIT);
            y = random(s.getInitScreen().getScreenHeight() - COORD_LIMIT);
            enemy = new Sprite(s, ob, x, y, "enemy1");
        } while (ob.isIntercept(x - w, y - h, w, h));
        double[] ret = new double[2];
        ret[0] = x;
        ret[1] = y;
        LOGGER.info("Random coordinates has been initialized\n");
        return ret;
    }

    private static Sprite randomEnemy(Settings s, Obstacles ob) throws FileNotFoundException {
        double x, y;
        double w, h;
        do {
            x = random(s.getInitScreen().getScreenWidth() - COORD_LIMIT);
            y = random(s.getInitScreen().getScreenHeight() - COORD_LIMIT);
            enemy = new Sprite(s, ob, x, y, "enemy1");
            w = enemy.getMovementHandler().getSpriteLayout().getBoundsInLocal().getWidth();
            h = enemy.getMovementHandler().getSpriteLayout().getBoundsInLocal().getHeight();
        } while (ob.isIntercept(x - w, y - h, w, h));
        LOGGER.info("Random enemy has been initialized\n");
        return enemy;
    }

    public static void respawnEnemy(Sprite enemy, Settings s, Obstacles ob) throws FileNotFoundException {
        enemy.getMovementHandler().getWalkView().getAnimation().stop();
        double w = enemy.getMovementHandler().getSpriteLayout().getBoundsInLocal().getWidth();
        double h = enemy.getMovementHandler().getSpriteLayout().getBoundsInLocal().getHeight();
        double[] coords = randomCoords(w, h, s, ob);
        enemy.getMovementHandler().moveHeroTo(coords[0], coords[1]);
        enemy.getMovementHandler().getWalkView().initView("Walk");
        enemy.setHp(s.getHpStart());
        enemy.dmg = s.getDmgStart();
        enemy.setAgr(false);
        enemy.getMovementHandler().getWalkView().setAttack(false);
        try {
            Thread.sleep(0, 5);
        } catch (InterruptedException e) {
            LOGGER.info("Respawning enemies has been initialized successfully\n");
        }
    }
}
