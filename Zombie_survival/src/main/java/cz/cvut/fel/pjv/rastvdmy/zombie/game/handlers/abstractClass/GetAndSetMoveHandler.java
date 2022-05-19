package cz.cvut.fel.pjv.rastvdmy.zombie.game.handlers.abstractClass;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.animation.AnimationQueue;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.animation.AnimationView;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

public abstract class GetAndSetMoveHandler {

    private AnimationView walkView;
    private VBox spriteLayout;
    private ProgressBar pb;
    private AnimationQueue animationQueue;
    private double x, y;
    private double dx, dy;
    private boolean isStuck;
    private boolean isOverlaps;
    private int dir;

    public AnimationView getWalkView() {
        return walkView;
    }

    public void setWalkView(AnimationView walkView) {
        this.walkView = walkView;
    }

    public VBox getSpriteLayout() {
        return spriteLayout;
    }

    public void setSpriteLayout(VBox spriteLayout) {
        this.spriteLayout = spriteLayout;
    }

    public ProgressBar getPb() {
        return pb;
    }

    public void setPb(ProgressBar pb) {
        this.pb = pb;
    }

    public AnimationQueue getAnimationQueue() {
        return animationQueue;
    }

    public void setAnimationQueue(AnimationQueue animationQueue) {
        this.animationQueue = animationQueue;
    }

    public double getCoordX() {
        return x;
    }

    public void setCoordX(double x) {
        this.x = x;
    }

    public double getCoordY() {
        return y;
    }

    public void setCoordY(double y) {
        this.y = y;
    }

    public double getDirX() {
        return dx;
    }

    public void setDirX(double dx) {
        this.dx = dx;
    }

    public double getDirY() {
        return dy;
    }


    public void setDirY(double dy) {
        this.dy = dy;
    }

    public void setEnemyId() {
    }

    public boolean isStuck() {
        return isStuck;
    }

    public void setStuck(boolean stuck) {
        isStuck = stuck;
    }

    public boolean isOverlaps() {
        return isOverlaps;
    }

    public void setOverlaps(boolean overlaps) {
        isOverlaps = overlaps;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }
}
