package cz.cvut.fel.pjv.rastvdmy.zombie.game.animation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.times;

public class AnimationQueueTest {
    AnimationQueue animationQueue = new AnimationQueue();

    @Test
    @DisplayName("Testing animation queue with mockito")
    public void testing_animationQueue() {
        AnimationQueue animationQueue = Mockito.mock(AnimationQueue.class);
        int endMock = 0;
        Mockito.when(animationQueue.getLast()).thenReturn(endMock);
        Mockito.verify(animationQueue, times(1));
    }

    @Test
    @DisplayName("Testing animation queue is not alive")
    public void testing_animationQueue_isNotAlive() {
        boolean isNotAlive = animationQueue.isAlive();
        assertFalse(isNotAlive);
    }

    @Test
    @DisplayName("Testing animation queue is not interrupted")
    public void testing_animationQueue_isNotInterrupted() {
        boolean isInt = animationQueue.isInterrupted();
        assertFalse(isInt);
    }

    @Test
    @DisplayName("Testing animation queue is not daemon")
    public void testing_animationQueue_isNotDaemon() {
        boolean isD = animationQueue.isDaemon();
        assertFalse(isD);
    }
}
