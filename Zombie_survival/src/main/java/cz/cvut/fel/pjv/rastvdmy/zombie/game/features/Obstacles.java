package cz.cvut.fel.pjv.rastvdmy.zombie.game.features;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.elements.Settings;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Obstacles {
    private final Rectangle[] obstacles = new Rectangle[200];
    private final Group obstaclesLayer = new Group();
    private final Settings settings;
    private final double imgWidth, imgHeight;

    public Group getObstaclesLayer() {
        return obstaclesLayer;
    }

    public Settings getSettings() {
        return settings;
    }

    public Obstacles(Settings settings, double imgWidth, double imgHeight) {
        this.settings = settings;
        this.imgWidth = imgWidth;
        this.imgHeight = imgHeight;
        initObstacles();
    }

    private void initObstacles() {
        int obstCounter = 0;
        Scanner scanner = new Scanner(Objects.requireNonNull(
                this.getClass().getResourceAsStream(getSettings().getObstaclesPath())));
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            data = data.replaceAll("<.*?>", "");
            if (data.equals("")) {
                continue;
            }
            String[] vStr = data.split(" ");
            int[] v = Arrays.stream(vStr).mapToInt(Integer::parseInt).toArray();
            Rectangle rect = new Rectangle(scale(v[0], 0), scale(v[1], 1), scale(v[2], 0),
                    scale(v[3], 1));
            obstacles[obstCounter] = rect;
            obstaclesLayer.getChildren().add(rect);
            obstCounter++;
        }
        int finalObstCounter = obstCounter;
        System.out.printf("OBST read %d\n", finalObstCounter);
        scanner.close();
    }

    public boolean isIntercept(double c1, double c2, double c3, double c4) {
        boolean flag = false;
        for (Rectangle obst : obstacles) {
            if (obst == null) {
                break;
            }
            if (obst.getBoundsInLocal().intersects(c1, c2, c3, c4)) {
                flag = true;
            }
        }
        return flag;
    }

    public boolean isIntercept(Shape shape) {
        boolean flag = true;
        for (Rectangle obst : obstacles) {
            if (obst == null) {
                break;
            }
            if (obst.getBoundsInLocal().intersects(shape.getBoundsInLocal())) {
                flag = false;
            }
        }
        return flag;
    }

    public double scale(double coord, int flag) {
        if (flag == 0) {
            return coord * settings.getInitScreen().getScreenWidth() / imgWidth;
        } else {
            return coord * settings.getInitScreen().getScreenHeight() / imgHeight;
        }
    }
}