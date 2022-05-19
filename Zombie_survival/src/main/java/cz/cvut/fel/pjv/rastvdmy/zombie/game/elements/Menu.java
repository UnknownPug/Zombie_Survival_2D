package cz.cvut.fel.pjv.rastvdmy.zombie.game.elements;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.launch.Main;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.logging.Logger;

public class Menu {
    public static final Logger LOGGER = Logger.getGlobal();
    public static final String LOGIN_MUSIC_PATH = "/gameFiles/music/login.mp3";
    public static final String QUIT_STAGE_PATH = "/gameFiles/gameElements/quitStage.png";
    public static final String LOGIN_SCREEN_PATH = "/gameFiles/loginScreen/loginScreen.jpg";

    private Main main;
    private Stage primaryStage;
    private String path;
    private String path2;
    private String path3;

    public Menu() {
        path = LOGIN_MUSIC_PATH;
        path2 = QUIT_STAGE_PATH;
        path3 = LOGIN_SCREEN_PATH;
    }

    public void getMain() {
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public String getPath() {
        return path;
    }

    public String getPath2() {
        return path2;
    }

    public String getPath3() {
        return path3;
    }

    private Pane getPane() {
        primaryStage = new Stage();
        primaryStage.setTitle("ZombieSurvival");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Pane root = createContent();
        path = LOGIN_MUSIC_PATH;
        LOGGER.info("Music has been initialized\n");
        return root;
    }

    private Stage getStage(ImageView quitImg, Button ok, Button quit, ToggleButton fullscreen, Text fullScreenTxt) {
        Group buttons = new Group(quitImg, fullScreenTxt, quit, ok, fullscreen);
        Stage stage = new Stage();
        stage.setScene(new Scene(buttons, 445, 250));
        stage.setX(420);
        stage.setY(290);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }

    private Text getText(ImageView quitImg) {
        Text fullScreenTxt = new Text(185, 90, "Full Screen");
        fullScreenTxt.setFill(Color.DARKGREY);
        fullScreenTxt.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        quitImg.setFitWidth(450);
        return fullScreenTxt;
    }

    private ToggleButton getToggleButton() {
        ToggleButton fullscreen = new ToggleButton("ON/OFF");
        fullscreen.setTextFill(Color.DARKGREY);
        fullscreen.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 15));
        fullscreen.setTranslateX(182);
        fullscreen.setTranslateY(112);
        fullscreen.setOnMouseClicked(event -> primaryStage.setMaximized(fullscreen.isSelected()));
        return fullscreen;
    }

    private Button getQuit() {
        Button quit = new Button("X");
        quit.setTextFill(Color.DARKGREY);
        quit.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 20));
        quit.setOnMouseEntered(event -> quit.setTextFill(Color.DARKRED));
        quit.setOnMouseExited(event -> quit.setTextFill(Color.DARKGREY));
        quit.setBackground(null);
        quit.setTranslateX(405);
        quit.setTranslateY(2);
        return quit;
    }

    private Button getButton() {
        Button ok = new Button("OK");
        ok.setTextFill(Color.DARKGREY);
        ok.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 18));
        ok.setOnMouseEntered(event -> ok.setTextFill(Color.DARKRED));
        ok.setOnMouseExited(event -> ok.setTextFill(Color.DARKGREY));
        ok.setBackground(null);
        ok.setTranslateX(355);
        ok.setTranslateY(200);
        return ok;
    }

    private ImageView getLoginImgView() {
        path3 = LOGIN_SCREEN_PATH;
        ImageView img = new ImageView(new Image(Objects.requireNonNull(
                this.getClass().getResource(path3)).toExternalForm()));
        LOGGER.info("Login_screen photo has been initialized successfully\n");
        return img;
    }

    private ImageView getQuitImgView() {
        path2 = QUIT_STAGE_PATH;
        ImageView imgBg = new ImageView(Objects.requireNonNull(this.getClass().getResource(path2)).toExternalForm());
        LOGGER.info("Quit_photo has been initialized successfully\n");
        return imgBg;
    }

    public Menu(Main main) {
        this.main = main;
        Pane root = getPane();
        Media media = new Media(Objects.requireNonNull(this.getClass().getResource(path)).toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.1);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        mediaPlayer.play();
        root.getChildren().add(mediaView);
        scene(root);
    }

    private void scene(Pane root) {
        Scene scene = new Scene(root);
        primaryStage.setX(0);
        primaryStage.setY(0);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Pane createContent() {
        Pane root = new Pane();
        root.setPrefSize(main.getSettings().getInitScreen().getScreenWidth(),
                main.getSettings().getInitScreen().getScreenHeight());

        ImageView quitImg = getQuitImgView();
        ImageView loginImgView = getLoginImgView();

        root.getChildren().add(loginImgView);

        Title title = new Title("Zombie Survival 2D");
        title.setTranslateX(75);
        title.setTranslateY(200);

        MenuItem ngItem = new MenuItem("NEW GAME");
        MenuItem optItem = new MenuItem("OPTIONS");
        MenuItem exItem = new MenuItem("EXIT");
        MenuBox menuBox = new MenuBox(ngItem, optItem, exItem);

        gameStartButton(ngItem);
        optionsButton(quitImg, optItem);
        exitButton(quitImg, exItem);

        menuBox.setTranslateX(95);
        menuBox.setTranslateY(300);
        root.getChildren().addAll(title, menuBox);
        return root;
    }

    private void exitButton(ImageView quitImg, MenuItem Item3) {
        Item3.setOnMousePressed(e -> {
            MenuItem confirm1 = new MenuItem("OK");
            MenuItem confirm2 = new MenuItem("CANCEL");
            MenuBox confirm = new MenuBox(confirm1, confirm2);
            confirm.setTranslateX(115);
            confirm.setTranslateY(120);
            Text text = new Text(110, 60, "Are you sure to quit?");
            text.setFill(Color.DARKGREY);
            text.setFont(Font.font("Verdana", 20));

            quitImg.setFitWidth(450);
            Group buttons = new Group(quitImg, confirm, text);
            Stage stage = new Stage();
            stage.setScene(new Scene(buttons, 445, 200));
            stage.setX(420);
            stage.setY(290);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);

            confirm1.setOnMouseClicked(event -> {
                stage.close();
                primaryStage.close();
            });
            confirm2.setOnMouseClicked(event -> stage.close());
            stage.show();
        });
    }

    private void optionsButton(ImageView quitImg, MenuItem Item2) {
        Item2.setOnMousePressed(e -> {
            Button ok = getButton();
            Button quit = getQuit();

            ToggleButton fullscreen = getToggleButton();
            Text fullScreenTxt = getText(quitImg);
            Stage stage = getStage(quitImg, ok, quit, fullscreen, fullScreenTxt);
            quit.setOnMouseClicked(event -> stage.close());
            ok.setOnMouseClicked(event -> stage.close());
            stage.show();
        });
    }

    private void gameStartButton(MenuItem Item1) {
        Item1.setOnMousePressed(e -> {
            try {
                main.getSceneHandler().makeScene();
                main.gameStart();
            } catch (IOException | URISyntaxException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    private static class Title extends StackPane {
        public Title(String name) {
            Rectangle bg = new Rectangle(480, 60);
            bg.setStroke(Color.BLACK);
            bg.setStrokeWidth(2);
            bg.setFill(null);

            Text text = new Text(name);
            text.setFill(Color.BLACK);
            text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 50));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);
        }
    }

    public static class MenuBox extends VBox {
        public MenuBox(MenuItem... items) {
            getChildren().add(createSeparator());

            for (MenuItem item : items) {
                getChildren().addAll(item, createSeparator());
            }
        }

        private Line createSeparator() {
            Line sep = new Line();
            sep.setEndX(200);
            sep.setStroke(Color.DARKGREY);
            return sep;
        }
    }

    public static class MenuItem extends StackPane {
        public MenuItem(String name) {
            LinearGradient gradient = new LinearGradient(0, 0, 1, 0,
                    true, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.DARKRED),
                    new Stop(0.1, Color.BLACK),
                    new Stop(0.9, Color.BLACK),
                    new Stop(1, Color.DARKRED));

            Rectangle bg = new Rectangle(200, 30);
            bg.setOpacity(0.4);

            Text text = new Text(name);
            text.setFill(Color.DARKGREY);
            text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));
            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);
            setOnMouseEntered(event -> {
                bg.setFill(gradient);
                text.setFill(Color.DARKRED);
            });
            setOnMouseExited(event -> {
                bg.setFill(Color.BLACK);
                text.setFill(Color.DARKGREY);
            });
            setOnMousePressed(event -> bg.setFill(Color.DARKGREY));
            setOnMouseReleased(event -> bg.setFill(gradient));
        }
    }
}