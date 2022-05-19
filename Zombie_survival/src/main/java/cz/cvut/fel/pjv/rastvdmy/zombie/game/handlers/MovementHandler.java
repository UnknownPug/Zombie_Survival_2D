package cz.cvut.fel.pjv.rastvdmy.zombie.game.handlers;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.animation.AnimationView;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.elements.Settings;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.features.Obstacles;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.features.Sprite;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.handlers.abstractClass.GetAndSetMoveHandler;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.io.FileNotFoundException;
import java.util.logging.Logger;

public class MovementHandler extends GetAndSetMoveHandler {
    public static final Logger LOGGER = Logger.getGlobal();
    public boolean[] moves = new boolean[4];

    public double speed;

    private final Obstacles obstacles;
    private final Settings set;

    public MovementHandler(Settings settings, Obstacles obstacles, String type) throws FileNotFoundException {
        this.set = settings;
        this.speed = set.getSpeedStart();
        setSpriteLayout(new VBox());
        setPb(new ProgressBar(1));
        getPb().setBackground(Background.EMPTY);
        setWalkView(new AnimationView(settings, type));
        getWalkView().initView("Walk");
        getPb().setPrefWidth(getWalkView().getSpW());
        getPb().setTranslateX(getWalkView().getSpW() / 2);
        getPb().setProgress(1);
        getPb().setPrefHeight(4);
        getWalkView().setX(0);
        getWalkView().setY(10);
        getSpriteLayout().getChildren().setAll(getPb(), getWalkView());
        if (type.equals("male")) {
            getPb().setVisible(false);
        }
        this.obstacles = obstacles;
        LOGGER.info("MovementHandler initialized successfully\n");
    }

    public void move() {
        int dx = 0, dy = 0;
        double w = getWalkView().getSpW();
        double h = getWalkView().getSpH();

        final double x = getSpriteLayout().getBoundsInLocal().getWidth() / 2 + getSpriteLayout().getLayoutX() - w / 2;
        final double y = getSpriteLayout().getBoundsInLocal().getHeight() / 2 + getSpriteLayout().getLayoutY();

        setCoordX(x);
        setCoordY(y);

        dy = getFirstMove(dy, w, h, x, y);
        dy = getSecondMove(dy, w, h, x, y);

        dx = getThirdMove(dx, w, h, x, y);
        dx = getFourthMove(dx, w, h, x, y);
        moveHeroBy(dx, dy);
    }

    private int getFirstMove(int dy, double w, double h, double x, double y) {
        if (moves[0]) {
            if (!obstacles.isIntercept(x, y - speed, w, h)) {
                dy -= speed;
            } else {
                setStuck(true);
            }
        }
        return dy;
    }

    private int getSecondMove(int dy, double w, double h, double x, double y) {
        if (moves[1]) {
            if (!obstacles.isIntercept(x, y + speed, w, h)) {
                dy += speed;
            } else {
                setStuck(true);
            }
        }
        return dy;
    }

    private int getThirdMove(int dx, double w, double h, double x, double y) {
        if (moves[2]) {
            if (!obstacles.isIntercept(x + speed, y, w, h)) {
                dx += speed;
            } else {
                setStuck(true);
            }
        }
        return dx;
    }

    private int getFourthMove(int dx, double w, double h, double x, double y) {
        if (moves[3]) {
            if (!obstacles.isIntercept(x - speed, y, w, h)) {
                dx -= speed;
            } else {
                setStuck(true);
            }
        }
        return dx;
    }

    public void moveAgr(Sprite sprite) throws FileNotFoundException {
        if (getWalkView().isActive()) {
            double dx, dy;
            double w = getWalkView().getSpW();
            double h = getWalkView().getSpH();
            final double x =
                    getSpriteLayout().getBoundsInLocal().getWidth() / 2 + getSpriteLayout().getLayoutX() - w / 2;
            final double y = getSpriteLayout().getBoundsInLocal().getHeight() / 2 + getSpriteLayout().getLayoutY();
            setCoordX(x);
            setCoordY(y);
            dx = sprite.getMovementHandler().getCoordX() - x;
            dy = sprite.getMovementHandler().getCoordY() - y;
            double hyp = Math.sqrt(dx * dx + dy * dy);
            dx /= hyp;
            dy /= hyp;
            dx *= speed * set.getAgrSpeedModifier();
            dy *= speed * set.getAgrSpeedModifier();
            setDirX(dx);
            setDirY(dy);
            int animationDir = findDirection(dx, dy);
            if (animationDir != getWalkView().getAnimationActive()) {
                AnimationView.stopAnimation(getWalkView());
                getWalkView().launchAnimation(animationDir, speed);
            }
            Rectangle enemy = new Rectangle(x, y, getWalkView().getSpW(), getWalkView().getSpH());
            Rectangle player = new Rectangle(
                    sprite.getMovementHandler().getCoordX() - 10, sprite.getMovementHandler().getCoordY(),
                    sprite.getMovementHandler().getWalkView().getCellWidth() + 10,
                    sprite.getMovementHandler().getWalkView().cellHeight);
            setOverlaps(player.intersects(enemy.getBoundsInLocal()));
            bool(dx, dy, w, h, x, y);
            getWalkView().setAnimationActive(animationDir);
        }
    }

    private void bool(double dx, double dy, double w, double h, double x, double y) {
        boolean xBool, yBool, x2Bool, y2Bool;
        xBool = obstacles.isIntercept(x + dx, y, w, h);
        yBool = obstacles.isIntercept(x, y + dy, w, h);
        x2Bool = obstacles.isIntercept(x + dx * 2, y, w, h);
        y2Bool = obstacles.isIntercept(x, y + dy * 2, w, h);
        if (xBool) {
            dx = 0;
            if (!x2Bool) {
                dy *= 2;
            }
        }
        if (yBool) {
            dy = 0;
            if (!y2Bool) {
                dx *= 2;
            }
        }
        if (!isOverlaps()) {
            moveHeroBy(dx, dy);
        } else if (getWalkView().isActive()) {
            getWalkView().setAttack(true);
        }
    }

    public static int findDirection(double dx, double dy) {
        double delta;
        int animationDir;
        boolean leftRight;
        if (Math.abs(dx) >= Math.abs(dy)) {
            delta = dx;
            leftRight = true;
        } else {
            delta = dy;
            leftRight = false;
        }
        if (!leftRight && delta < 0) {
            animationDir = 0;
        } else if (!leftRight && delta > 0) {
            animationDir = 1;
        } else if (leftRight && delta > 0) {
            animationDir = 2;
        } else {
            animationDir = 3;
        }
        return animationDir;
    }

    public static int reflectDirection(int dir) {
        int result;
        switch (dir) {
            case 0 -> result = 1;
            case 1 -> result = 0;
            case 2 -> result = 3;
            case 3 -> result = 2;
            default -> result = -1;
        }
        return result;
    }

    private void moveHeroBy(double dx, double dy) {
        if (dx == 0 && dy == 0) {
            return;
        }
        final double cx = getSpriteLayout().getBoundsInLocal().getWidth() / 2;
        final double cy = getSpriteLayout().getBoundsInLocal().getHeight() / 2;

        double x = cx + getSpriteLayout().getLayoutX() + dx;
        double y = cy + getSpriteLayout().getLayoutY() + dy;
        moveHeroTo(x, y);
    }

    public void moveHeroTo(double x, double y) {
        final double cx = getSpriteLayout().getBoundsInLocal().getWidth() / 2;
        final double cy = getSpriteLayout().getBoundsInLocal().getHeight() / 2;
        if (x - cx >= 0 &&
                x + cx <= set.getInitScreen().getScreenWidth() &&
                y - cy >= 0 &&
                y + cy <= set.getInitScreen().getScreenHeight()) {
            getSpriteLayout().relocate(x - cx, y - cy);
        }
    }
}