package cz.cvut.fel.pjv.rastvdmy.zombie.game.features;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.item.ItemInfo;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.logging.Logger;

public class Indicator {
    public static final Logger LOGGER = Logger.getGlobal();
    public static final int X_COORD = 575;

    private final ImageView imageView;
    private final Label price, stat, desc, name;
    private final Group objects;
    private ItemInfo item;
    private Button cell;

    public ItemInfo getItem() {
        return item;
    }

    public Group getObjects() {
        return objects;
    }

    public Button getCell() {
        return cell;
    }

    public Label getPrice() {
        return price;
    }

    public Label getStat() {
        return stat;
    }

    public Label getDesc() {
        return desc;
    }

    public Label getName() {
        return name;
    }

    public Indicator() {
        item = new ItemInfo();
        name = new Label();
        price = new Label();
        stat = new Label();
        desc = new Label();
        imageView = new ImageView();
        imageView.setFitWidth(90);
        imageView.setFitHeight(90);
        imageView.setX(X_COORD);
        imageView.setY(99);

        setLabel(X_COORD, 219, getName());
        setLabel(X_COORD, 239, getPrice());
        setLabel(X_COORD, 259, getStat());
        setLabel(X_COORD, 279, getDesc());

        objects = new Group(imageView, price, getStat(), getDesc(), getName());
    }

    public void setItem(ItemInfo item, Button button) {
        this.cell = button;
        this.item = item;
        imageView.setImage(item.getIcon().getImage());
        imageView.setViewport(item.getIcon().cellClips[item.getId()]);
        name.setText(item.getName());
        price.setText("Price: " + item.getPrice());
        stat.setText(item.getStats());
        desc.setText(item.getDesc());
    }

    private void setLabel(int x, int y, Label l) {
        l.setLayoutX(x);
        l.setLayoutY(y);
        l.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 14));
        l.setTextFill(Color.WHITE);
    }
}
