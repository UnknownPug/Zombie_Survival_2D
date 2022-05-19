package cz.cvut.fel.pjv.rastvdmy.zombie.game.item;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.features.Indicator;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.animation.AnimationView;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ItemCell extends Button {
    public static final int PREFERRED_HEIGHT = 60;
    public static final int PREFERRED_WIDTH = 60;
    private final Button button;
    private final ItemInfo item;
    private final Indicator indicator;

    public Button getButton() {
        return button;
    }

    public ItemCell(ItemInfo item, Indicator indicator) {
        this.item = item;
        this.indicator = indicator;
        button = new Button();
        button.setDisable(item.isUsed());
        button.setPrefSize(PREFERRED_HEIGHT, PREFERRED_WIDTH);
        AnimationView icon = item.getIcon();
        button.setOnMouseClicked(event -> this.indicator.setItem(this.item, button));
        ImageView background = new ImageView(icon.getImage());
        background.setFitWidth(PREFERRED_WIDTH);
        background.setFitHeight(PREFERRED_HEIGHT);
        background.setViewport(icon.getViewport());
        button.setGraphic(background);
    }
}
