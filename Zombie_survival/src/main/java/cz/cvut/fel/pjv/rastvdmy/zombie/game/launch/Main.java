package cz.cvut.fel.pjv.rastvdmy.zombie.game.launch;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.elements.Menu;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.elements.Settings;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.features.Sprite;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.gui.GUI;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.handlers.BattleHandler;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.handlers.SceneHandler;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.animation.AnimationView;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class Main extends Application {
    public static final Logger LOGGER = Logger.getGlobal();

    private SceneHandler sceneHandler;
    private AnimationTimer timer;

    private Settings settings;

    private GUI gui;

    public SceneHandler getSceneHandler() {
        return sceneHandler;
    }

    public AnimationTimer getTimer() {
        return timer;
    }

    public Settings getSettings() {
        return settings;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        settings = new Settings();
        sceneHandler = new SceneHandler();
        Menu menu = new Menu(this);
    }

    public void gameStart() throws IOException, URISyntaxException {
        gui = new GUI(this);
        System.out.println(Thread.currentThread().getName());
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                try {
                    gui.refresh();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                sceneButtonInteraction();
                if (sceneHandler.getSprite().getMovementHandler().getWalkView().isActive()) {
                    sceneHandler.getSprite().getMovementHandler().move();
                }
                int counter = 0;
                for (Sprite enemy : sceneHandler.getEnemyHandler().getEnemies()) {
                    long current_time = System.currentTimeMillis();
                    enemy.proximityCheck(sceneHandler.getSprite());
                    if (!enemy.isAgr()) {
                        enemyMovementInit(enemy, current_time);
                    } else {
                        try {
                            enemy.getMovementHandler().moveAgr(sceneHandler.getSprite());
                            if (enemy.getMovementHandler().getWalkView().isAttack()) {
                                BattleHandler.launchAttack(
                                        enemy, sceneHandler.getSprite(),
                                        enemy.getMovementHandler().getAnimationQueue());
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    sceneInit(counter, enemy);
                    counter++;
                }
            }

            private void sceneButtonInteraction() {
                if (sceneHandler.getShop().isOpened()) {
                    this.stop();
                    sceneHandler.getShop().getScene().setOnKeyPressed(event -> {
                        if (event.getCode() == KeyCode.ESCAPE) {
                            sceneHandler.getShop().getStage().close();
                            sceneHandler.getShop().setOpened(false);
                            this.start();
                        }
                    });
                    sceneHandler.getShop().getShopExit().setOnMouseClicked(event -> {
                        sceneHandler.getShop().getStage().close();
                        sceneHandler.getShop().setOpened(false);
                        this.start();
                    });
                }
            }
        };
        timer.start();
        LOGGER.info("Game has been initialized successfully! Enjoy your game!\n");
    }

    private void enemyMovementInit(Sprite enemy, long current_time) {
        if (enemy.getTime() == 0) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 4);
            enemy.getMovementHandler().moves = new boolean[4];
            enemy.getMovementHandler().moves[randomNum] = true;
            enemy.getMovementHandler().setDir(randomNum);
            enemy.setTime(current_time);
            enemy.getMovementHandler().getWalkView().launchAnimation(
                    enemy.getMovementHandler().getDir(), enemy.getMovementHandler().speed);
        }
        if (current_time - enemy.getTime() >= 3000 || enemy.getMovementHandler().isStuck()) {
            enemy.setTime(0);
            AnimationView.stopAnimation(enemy.getMovementHandler().getWalkView());
            if (enemy.getMovementHandler().isStuck()) {
                enemy.getMovementHandler().setStuck(false);
            }
        }
        enemy.getMovementHandler().move();
    }

    private void sceneInit(int counter, Sprite enemy) {
        sceneHandler.line[counter].setStartX(enemy.getMovementHandler().getCoordX());
        sceneHandler.line[counter].setStartY(enemy.getMovementHandler().getCoordY());
        sceneHandler.line[counter].setEndX(sceneHandler.getSprite().getMovementHandler().getCoordX());
        sceneHandler.line[counter].setEndY(sceneHandler.getSprite().getMovementHandler().getCoordY());
    }
}
