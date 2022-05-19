package cz.cvut.fel.pjv.rastvdmy.zombie.game.handlers;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.animation.AnimationQueue;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.elements.Settings;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.features.Obstacles;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.features.Sprite;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.gui.GUIInfo;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.handlers.abstractClass.GetAndSetSceneHandler;
import javafx.animation.Animation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;

public class SceneHandler extends GetAndSetSceneHandler {
    public static final Logger LOGGER = Logger.getGlobal();
    public static final String STYLE_PATH = "/style.css";
    public static final int ENEMIES_NUMBER = 10;

    public Line[] line;
    private final AnimationQueue animationQueue;
    private final Stage stage;
    private final Image bgImg;
    private KeyCode prev;
    private Label label;
    private Group lines;

    public SceneHandler() throws IOException {
        setSettings(new Settings());
        stage = new Stage();
        stage.setFullScreen(false);
        bgImg = new Image(Objects.requireNonNull(this.getClass().getResource(
                getSettings().getBgPath())).toExternalForm());
        double imgWidth = bgImg.getWidth();
        double imgHeight = bgImg.getHeight();
        setObstacles(new Obstacles(getSettings(), imgWidth, imgHeight));
        animationQueue = new AnimationQueue();
        setEnemyHandler(new EnemyHandler(getSettings(), getObstacles()));
        setSprite(new Sprite(getSettings(), getObstacles(), getObstacles().scale(640, 0),
                getObstacles().scale(1030, 1), "male"));
        setShop(new ShopHandler(getSettings(), getSprite()));
    }

    public void makeScene() throws IOException, URISyntaxException {
        ImageView bgView = new ImageView(bgImg);
        bgView.setFitHeight(getSettings().getInitScreen().getScreenHeight());
        bgView.setFitWidth(getSettings().getInitScreen().getScreenWidth());

        ImageView bg2View = new ImageView(new Image(
                Objects.requireNonNull(this.getClass().getResource(
                        getSettings().getBgOverlayPath())).toExternalForm()));
        bg2View.setFitHeight(getSettings().getInitScreen().getScreenHeight());
        bg2View.setFitWidth(getSettings().getInitScreen().getScreenWidth());

        label = new Label("dx dy");
        label.setTextFill(Color.WHITE);

        setIndicator(new GUIInfo(getSettings()));
        getObstacles().getObstaclesLayer().setVisible(false);
        Group dungeon = new Group(bgView, getObstacles().getObstaclesLayer());
        enemyMovementHandler(dungeon);
        dungeon.getChildren().add(getSprite().getMovementHandler().getSpriteLayout());
        dungeon.getChildren().add(bg2View);
        dungeon.getChildren().add(getIndicator().getObject());
        lines = new Group();
        line = new Line[ENEMIES_NUMBER];
        for (int i = 0; i < ENEMIES_NUMBER; i++) {
            line[i] = new Line(0, 0, 0, 0);
            line[i].setStroke(Color.RED);
            line[i].setFill(Color.RED);
            lines.getChildren().add(line[i]);
        }
        lines.setVisible(false);
        dungeon.getChildren().add(lines);
        Scene scene = new Scene(dungeon, getSettings().getInitScreen().getScreenWidth(),
                getSettings().getInitScreen().getScreenHeight());
        prev = KeyCode.M;
        keyPressedScene(scene);
        keyReleasedScene(scene);
        scene.getStylesheets().add((Objects.requireNonNull(
                this.getClass().getResource(STYLE_PATH)).toExternalForm()));
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setX(0);
        stage.setY(0);
        stage.show();
        LOGGER.info("Scene maker has been initialized successfully\n");
    }

    private void enemyMovementHandler(Group dungeon) {
        for (Sprite enemy : getEnemyHandler().getEnemies()) {
            enemy.getMovementHandler().getWalkView().setOnMouseClicked(event -> {
                try {
                    if (enemy.getMovementHandler().isOverlaps()) {
                        BattleHandler.launchAttack(getSprite(), enemy, animationQueue);
                    }
                } catch (FileNotFoundException exception) {
                    exception.printStackTrace();
                }
            });
            dungeon.getChildren().add(enemy.getMovementHandler().getSpriteLayout());
            resize(enemy);
        }
    }

    private void buttonSwitchCase(KeyEvent event) {
        switch (event.getCode()) {
            case W, UP -> {
                if (event.getCode() != prev) {
                    animationQueue.add(0);
                }
                getSprite().getMovementHandler().moves[0] = true;
                label.setText("PRESSED W");
            }
            case S, DOWN -> {
                if (event.getCode() != prev) {
                    animationQueue.add(1);
                }
                getSprite().getMovementHandler().moves[1] = true;
                label.setText("PRESSED S");
            }
            case D, RIGHT -> {
                if (event.getCode() != prev) {
                    animationQueue.add(2);
                }
                getSprite().getMovementHandler().moves[2] = true;
                label.setText("PRESSED D");
            }
            case A, LEFT -> {
                if (event.getCode() != prev) {
                    animationQueue.add(3);
                }
                getSprite().getMovementHandler().moves[3] = true;
                label.setText("PRESSED A");
            }
            case R -> stopAnimation();
            case M -> {
                try {
                    getShop().open(getIndicator());
                    stopAnimation();
                    getSprite().getMovementHandler().moves = new boolean[4];
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            case ESCAPE -> System.exit(0);
            case T -> lines.setVisible(!lines.isVisible());
            case O -> getObstacles().getObstaclesLayer().setVisible(!getObstacles().getObstaclesLayer().isVisible());
        }
    }

    private void initAnimation(KeyEvent event) {
        switch (event.getCode()) {
            case UP, W -> {
                animationQueue.delete(0);
                getSprite().getMovementHandler().moves[0] = false;
            }
            case DOWN, S -> {
                animationQueue.delete(1);
                getSprite().getMovementHandler().moves[1] = false;
            }
            case RIGHT, D -> {
                animationQueue.delete(2);
                getSprite().getMovementHandler().moves[2] = false;
            }
            case LEFT, A -> {
                animationQueue.delete(3);
                getSprite().getMovementHandler().moves[3] = false;
            }
        }
    }

    private void keyPressedScene(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (getSprite().getMovementHandler().getWalkView().isActive() && !getShop().isOpened()) {
                buttonSwitchCase(event);
                if (event.getCode() != prev) {
                    prev = event.getCode();
                    stopAnimation();
                    getSprite().getMovementHandler().getWalkView().launchAnimation(
                            animationQueue.getLast(), getSprite().getMovementHandler().speed);
                }
            }
        });
    }

    private void keyReleasedScene(Scene scene) {
        scene.setOnKeyReleased(event -> {
            if (getSprite().getMovementHandler().getWalkView().isActive()) {
                prev = KeyCode.ASTERISK;
                initAnimation(event);
                stopAnimation();
                if (animationQueue.getLast() != -1
                        && !Arrays.equals(getSprite().getMovementHandler().moves, new boolean[4])) {
                    getSprite().getMovementHandler().getWalkView().launchAnimation(animationQueue.getLast(),
                            getSprite().getMovementHandler().speed);
                } else {
                    getSprite().getMovementHandler().getWalkView().setViewport(
                            getSprite().getMovementHandler().getWalkView().cellClips[0]);
                }
            }
        });
    }

    private void resize(Sprite sprite) {
        sprite.getMovementHandler().getWalkView().cellHeight *= 1;
        sprite.getMovementHandler().getWalkView().cellWidth *= 1;
        sprite.getMovementHandler().getWalkView().setFitHeight(
                sprite.getMovementHandler().getWalkView().getCellHeight());
        sprite.getMovementHandler().getWalkView().setFitWidth(
                sprite.getMovementHandler().getWalkView().getCellWidth());
        sprite.getMovementHandler().getWalkView().countSpSize();
    }

    private void stopAnimation() {
        try {
            if (getSprite().getMovementHandler().getWalkView()
                    .getAnimation().getStatus().equals(Animation.Status.RUNNING)) {
                getSprite().getMovementHandler().getWalkView().getAnimation().stop();
            }
        } catch (NullPointerException ignored) {
            LOGGER.info("Animation is not paused\n");
        }
        if (animationQueue.getLast() == -1) {
            getSprite().getMovementHandler().getWalkView().setViewport(
                    getSprite().getMovementHandler().getWalkView().cellClips[0]);
        }
    }
}