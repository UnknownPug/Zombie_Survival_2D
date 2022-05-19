package cz.cvut.fel.pjv.rastvdmy.zombie.game.animation;

public interface AnimationInterface {
    boolean isEmpty();

    void reset();

    int getLast();

    void delete(int value);

    void add(int value);
}
