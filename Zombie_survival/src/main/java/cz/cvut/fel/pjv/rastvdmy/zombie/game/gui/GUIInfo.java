package cz.cvut.fel.pjv.rastvdmy.zombie.game.gui;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.elements.Settings;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.features.Sprite;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Objects;
import java.util.logging.Logger;

public class GUIInfo {
    public static final Logger LOGGER = Logger.getGlobal();

    private final HBox object;
    private final Label hp, money, food, water, dmg, speed;

    public HBox getObject() {
        return object;
    }

    public GUIInfo(Settings settings) {
        object = new HBox(40);

        hp = new Label(settings.getHpStart() + " / " + settings.getHpStart());
        hp.setGraphic(setIcon(settings.getHealthIconPath()));
        setStyle(hp, "red");

        money = new Label(Integer.toString(settings.getMoneyStart()));
        money.setGraphic(setIcon(settings.getMoneyIconPath()));
        setStyle(money, "green");

        food = new Label(Integer.toString(settings.getFoodStart()));
        food.setGraphic(setIcon(settings.getFoodIconPath()));
        setStyle(food, "brown");

        water = new Label(Integer.toString(settings.getWaterStart()));
        water.setGraphic(setIcon(settings.getWaterIconPath()));
        setStyle(water, "blue");

        dmg = new Label(Integer.toString(settings.getDmgStart()));
        dmg.setGraphic(setIcon(settings.getDmgIconPath()));
        setStyle(dmg, "red");

        speed = new Label(Double.toString(settings.getSpeedStart()));
        speed.setGraphic(setIcon(settings.getSpeedIconPath()));
        setStyle(speed, "yellow");

        object.setLayoutX(0);
        object.setLayoutY(0);
        object.setPrefWidth(settings.getInitScreen().getScreenWidth());
        object.setPrefHeight(50);

        Image img = new Image(Objects.requireNonNull(
                this.getClass().getResource(settings.getUpperMenuPath())).toExternalForm());
        BackgroundImage image = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0,
                        true, true, false, false));
        object.setBackground(new Background(image));
        object.getChildren().addAll(hp, money, food, water, dmg, speed);
        System.out.println("GUI information has been initialized successfully\n");
    }

    public ImageView setIcon(String path) {
        Image image1 = new Image(Objects.requireNonNull(this.getClass().getResource(path)).toExternalForm());
        ImageView imageView = new ImageView(image1);
        imageView.setFitHeight(30);
        imageView.setFitWidth(20);
        return imageView;
    }

    private void setStyle(Label label, String color) {
        label.setStyle(String.format("-fx-font: 20 arial; -fx-text-fill: %s;", color));
        label.setTranslateY(7);
        label.setTranslateX(10);
    }

    public void refresh(Sprite sprite) {
        hp.setText(sprite.getHp() + " / " + sprite.maxHp);
        money.setText(Integer.toString(sprite.money));
        food.setText(Integer.toString(sprite.food));
        water.setText(Integer.toString(sprite.water));
        dmg.setText(Integer.toString(sprite.dmg));
        speed.setText(Double.toString(sprite.getMovementHandler().speed));
        money.setText(Integer.toString(sprite.money));
    }
}
