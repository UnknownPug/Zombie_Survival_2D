package cz.cvut.fel.pjv.rastvdmy.zombie.game.gui;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.launch.Main;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.elements.Menu;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.features.Sprite;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.handlers.EnemyHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class GUI {
    public static final String QUIT_STAGE_PATH = "/gameFiles/gameElements/quitStage.png";
    private final Main main;
    private String deathImg;
    private double timeLast;

    public GUI(Main main) {
        deathImg = QUIT_STAGE_PATH;
        this.main = main;
    }

    public String getDeathImg() {
        return deathImg;
    }

    public void refresh() throws FileNotFoundException {
        suppliesHandler();
        refreshEnemiesHealth();
        main.getSceneHandler().getIndicator().refresh(main.getSceneHandler().getSprite());
        deathCheck();
    }

    private void refreshEnemiesHealth() throws FileNotFoundException {
        Sprite[] enemies = main.getSceneHandler().getEnemyHandler().getEnemies();
        for (int i = 0; i < main.getSceneHandler().getEnemyHandler().getEnemies().length; i++) {
            enemies[i].getMovementHandler().getPb().setProgress(enemies[i].getHp() / (float) (enemies[i].maxHp));
            if (enemies[i].getHp() == 0) {
                EnemyHandler.respawnEnemy(enemies[i],
                        main.getSceneHandler().getSettings(), main.getSceneHandler().getObstacles());
                main.getSceneHandler().getSprite().money += main.getSceneHandler().getSettings().getZombie1Yield();
            }
        }
    }

    private void deathCheck() {
        if (main.getSceneHandler().getSprite().getHp() == 0) {
            main.getTimer().stop();
            initDeathWindow();
        }
    }

    private void initDeathWindow() {
        deathImg = QUIT_STAGE_PATH;
        Menu.MenuItem confirm1 = new Menu.MenuItem("Leave");
        Menu.MenuBox confirm = new Menu.MenuBox(confirm1);
        confirm.setTranslateX(115);
        confirm.setTranslateY(120);
        Text text = new Text(110, 60, "You DIED!");
        text.setFill(Color.DARKGREY);
        text.setFont(Font.font("Verdana", 30));
        AtomicReference<ImageView> imgBg = new AtomicReference<>(new ImageView(new Image(
                Objects.requireNonNull(Objects.requireNonNull(
                        this.getClass().getResource(deathImg)).toExternalForm()))));
        Thread thread = new Thread(() -> {
            try (InputStream stream = Files.newInputStream(Paths.get(main.getSettings().getQuitDialoguePath()))) {
                Thread.sleep(1000);
                imgBg.set(new ImageView(new Image(stream)));
            } catch (IOException ioException) {
                System.out.println("Couldn't load image");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        imgBg.get().setFitWidth(450);
        Group buttons = new Group(imgBg.get(), confirm, text);
        Stage stage = new Stage();
        stage.setScene(new Scene(buttons, 445, 200));
        stage.setX(420);
        stage.setY(290);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);

        confirm1.setOnMouseClicked(event -> {
            stage.close();
            System.exit(0);
        });
        stage.show();
    }

    private void suppliesHandler() {
        double timeNew = System.currentTimeMillis();
        if (timeLast == 0) {
            timeLast = timeNew;
        }
        if (timeNew - timeLast >= main.getSceneHandler().getSettings().getSupplyConsumptionTime()) {
            main.getSceneHandler().getSprite().water--;
            main.getSceneHandler().getSprite().food--;
            timeLast = 0;
        }
    }
}