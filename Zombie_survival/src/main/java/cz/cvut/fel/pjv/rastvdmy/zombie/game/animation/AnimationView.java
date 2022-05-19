package cz.cvut.fel.pjv.rastvdmy.zombie.game.animation;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.elements.Settings;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.logging.Logger;

public class AnimationView extends ImageView {
    public static final Logger LOGGER = Logger.getGlobal();

    public Rectangle2D[] cellClips;
    public double cellWidth, cellHeight;

    private final IntegerProperty frameCounter = new SimpleIntegerProperty(0);

    private final Settings settings;
    private final String baseType;
    private Timeline animation;
    private Duration frameTime;
    private Image img;
    private String type;
    private int[] numCells;
    private int iterations, animationActive;
    private double spW, spH;
    private boolean isActive, isAttack;

    public Timeline getAnimation() {
        return animation;
    }

    public String getBaseType() {
        return baseType;
    }

    public boolean isActive() {
        return !isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isAttack() {
        return isAttack;
    }

    public void setAttack(boolean attack) {
        isAttack = attack;
    }

    public int getAnimationActive() {
        return animationActive;
    }

    public void setAnimationActive(int animationActive) {
        this.animationActive = animationActive;
    }

    public double getCellWidth() {
        return cellWidth;
    }

    public double getCellHeight() {
        return cellHeight;
    }

    public double getSpW() {
        return spW;
    }

    public double getSpH() {
        return spH;
    }

    public AnimationView(Settings settings, String type) {
        this.settings = settings;
        baseType = type;
        this.type = baseType + "Walk";
        LOGGER.info("Animation starts working\n");
    }

    public void initView(String actionType) throws FileNotFoundException {
        action(actionType);
        prepareImages(actionType);
        widthInit(img, numCells);
        LOGGER.info("First view initialization has been accomplished\n");
    }

    public void initView(Image img, int[] numCells) {
        this.img = img;
        this.animationActive = -1;
        this.numCells = numCells;
        iterations = numCells[0] * numCells[1];
        widthInit(img, numCells);
        LOGGER.info("Second view initialization has been accomplished\n");
    }

    private void action(String actionType) {
        this.type = baseType + actionType;
        isAttack = false;
        this.animationActive = -1;
    }

    private void widthInit(Image img, int[] numCells) {
        double cellWidthInit = img.getWidth() / numCells[0];
        double cellHeightInit = img.getHeight() / numCells[1];
        cellWidth = cellWidthInit;
        cellHeight = cellHeightInit;
        countSpSize();
        cellClips = new Rectangle2D[numCells[0] * numCells[1]];
        int counter = 0;
        for (int j = 0; j < numCells[1]; j++) {
            for (int i = 0; i < numCells[0]; i++) {
                cellClips[counter] = new Rectangle2D(
                        i * cellWidthInit, j * cellHeightInit,
                        cellWidthInit, cellHeightInit
                );
                counter++;
            }
        }
        setImage(img);
        setViewport(cellClips[0]);
    }

    private void prepareImages(String actionType) {
        if (baseType.equals("male")) {
            switch (actionType) {
                case "Walk" -> walkCase(this.numCells = settings.getMaleWalkCells());
                case "Attack" -> attackCase(this.numCells = settings.getMaleAttackCells());
                case "Death" -> dieCase(this.numCells = settings.getMaleDieCells());
            }
        } else if (baseType.equals("enemy1")) {
            switch (actionType) {
                case "Walk" -> enemyWalkCase(this.numCells = settings.getEnemy1WalkCells());
                case "Attack" -> enemyAttackCase(this.numCells = settings.getEnemy1AttackCells());
                case "Death" -> enemyDieCase(this.numCells = settings.getEnemy1DieCells());
            }
        }
        LOGGER.info("Images initialization has been accomplished\n");
    }

    // Male animation case
    private void walkCase(int[] numCells) {
        this.numCells = numCells;
        this.img = new Image(Objects.requireNonNull(this.getClass()
                .getResource(settings.getMaleWalkSpritePath())).toExternalForm());
        iterations = Animation.INDEFINITE;
        isActive = false;
    }

    private void attackCase(int[] numCells) {
        this.img = new Image(Objects.requireNonNull(this.getClass()
                .getResource(settings.getMaleAttackSpritePath())).toExternalForm());
        iterations = numCells[0];
        isActive = true;
    }

    private void dieCase(int[] numCells) {
        this.img = new Image(Objects.requireNonNull(this.getClass()
                .getResource(settings.getMaleDieSpritePath())).toExternalForm());
        iterations = numCells[0];
        isActive = true;
    }

    // >>> Zombie animation case
    private void enemyWalkCase(int[] numCells) {
        this.numCells = numCells;
        this.img = new Image(Objects.requireNonNull(this.getClass()
                .getResource(settings.getEnemy1WalkSpritePath())).toExternalForm());
        iterations = Animation.INDEFINITE;
        isActive = false;
    }

    private void enemyAttackCase(int[] numCells) {
        this.img = new Image(Objects.requireNonNull(this.getClass()
                .getResource(settings.getEnemy1AttackSpritePath())).toExternalForm());
        iterations = numCells[0];
        isActive = true;
    }

    private void enemyDieCase(int[] numCells) {
        this.img = new Image(Objects.requireNonNull(this.getClass()
                .getResource(settings.getEnemy1DieSpritePath())).toExternalForm());
        iterations = numCells[0];
        isActive = true;
    }

    private void moveUP(double speed) {
        frameTime = Duration.seconds(settings.getFrameTime() / speed);
        frameCounter.set(numCells[0] * 3 + 1);
        animation = new Timeline(
                new KeyFrame(frameTime, event -> {
                    setViewport(cellClips[frameCounter.get()]);
                    if (frameCounter.get() == numCells[0] * numCells[1] - 1) {
                        frameCounter.set(numCells[0] * 3 + 1);
                    } else {
                        frameCounter.set((frameCounter.get() + 1) % (numCells[0] * numCells[1]));
                    }
                })
        );
        CycleCountSet();
    }

    private void CycleCountSet() {
        animation.setCycleCount(iterations);
        animation.setOnFinished(event -> {
            isActive = false;
            try {
                if (!type.equals("maleWalk")) {
                    initView("Walk");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        animation.play();
    }

    private void moveDOWN(double speed) {
        frameTime = Duration.seconds(settings.getFrameTime() / speed);
        frameCounter.set(1);
        animation = new Timeline(
                new KeyFrame(frameTime, event -> {
                    setViewport(cellClips[frameCounter.get()]);
                    frameCounter.set((frameCounter.get() + 1) % (numCells[0]));
                })
        );
        CycleCountSet();
    }

    private void moveLEFT(double speed) {
        frameTime = Duration.seconds(settings.getFrameTime() / speed);
        frameCounter.set(numCells[0] + 1);
        animation = new Timeline(
                new KeyFrame(frameTime, event -> {
                    setViewport(cellClips[frameCounter.get()]);
                    if (frameCounter.get() == numCells[0] * 2 - 1) {
                        frameCounter.set(numCells[0] + 1);
                    } else {
                        frameCounter.set((frameCounter.get() + 1) % (numCells[0] * 2));
                    }
                })
        );
        CycleCountSet();
    }

    private void moveRIGHT(double speed) {
        frameTime = Duration.seconds(settings.getFrameTime() / speed);
        frameCounter.set(numCells[0] * 2 + 1);
        animation = new Timeline(
                new KeyFrame(frameTime, event -> {
                    setViewport(cellClips[frameCounter.get()]);
                    if (frameCounter.get() == numCells[0] * 3 - 1) {
                        frameCounter.set(numCells[0] * 2 + 1);
                    } else {
                        frameCounter.set((frameCounter.get() + 1) % (numCells[0] * 3));
                    }
                })
        );
        CycleCountSet();
    }

    public void countSpSize() {
        spW = cellWidth * 0.5;
        spH = cellHeight * 0.5;
    }

    public static void stopAnimation(AnimationView walk_view) {
        try {
            if (walk_view.animation.getStatus().equals(Animation.Status.RUNNING)) {
                walk_view.animation.stop();
            }
        } catch (NullPointerException ignored) {
        }
    }

    public void launchAnimation(int dir, double speed) {
        switch (dir) {
            case 0 -> moveUP(speed);
            case 1 -> moveDOWN(speed);
            case 2 -> moveRIGHT(speed);
            case 3 -> moveLEFT(speed);
        }
    }
}