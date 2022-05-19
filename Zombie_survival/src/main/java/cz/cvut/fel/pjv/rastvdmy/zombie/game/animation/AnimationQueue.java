package cz.cvut.fel.pjv.rastvdmy.zombie.game.animation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnimationQueue extends Thread implements AnimationInterface {
    public static final Logger LOGGER = Logger.getLogger(AnimationQueue.class.getName());
    public static final String NULL_MESSAGE = null;
    public static final int SECONDS = 1000;

    private int[] queue;
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    public AnimationQueue() {
        executorService.execute(() -> {
            try {
                queue = new int[4];
                sleep(SECONDS);
                reset();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.shutdown();
    }

    public boolean isEmpty() {
        boolean flag = true;
        for (int q : queue) {
            if (q != -1) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public void reset() {
        for (int i = 0; i < 4; i++)
            queue[i] = -1;
    }

    public int getLast() {
        return queue[0];
    }

    public void delete(int value) {
        if (isEmpty()) {
            return;
        }
        int[] copy = new int[queue.length - 1];
        var ref = new Object() {
            int index;
        };
        Thread thread1 = new Thread(() -> {
            try {
                sleep(SECONDS);
                for (ref.index = 0; ref.index < queue.length; ref.index++) {
                    if (queue[ref.index] == value) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, NULL_MESSAGE, e);
            }
        });
        if (ref.index == queue.length - 1 && queue[ref.index] != value) {
            return;
        }
        Thread thread2 = new Thread(() -> {
            try {
                sleep(SECONDS);
                for (int i = 0, j = 0; i < queue.length; i++) {
                    if (i != ref.index) {
                        if (j == copy.length) {
                            return;
                        }
                        copy[j] = queue[i];
                        j++;
                    }
                }
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, NULL_MESSAGE, e);
            }
        });
        thread1.start();
        thread2.start();
        queue = new int[4];
        reset();
        System.arraycopy(copy, 0, queue, 0, copy.length);
    }

    public void add(int value) {
        if (queue.length - 1 >= 0) {
            System.arraycopy(queue, 0, queue, 1, queue.length - 1);
        }
        queue[0] = value;
    }
}