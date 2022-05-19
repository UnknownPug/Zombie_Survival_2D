package cz.cvut.fel.pjv.rastvdmy.zombie.game.handlers;

import cz.cvut.fel.pjv.rastvdmy.zombie.game.elements.Settings;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.features.Indicator;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.features.Sprite;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.item.ItemInfo;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.item.ItemCell;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.animation.AnimationView;
import cz.cvut.fel.pjv.rastvdmy.zombie.game.gui.GUIInfo;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import javafx.stage.StageStyle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.logging.Logger;


public class ShopHandler {
    public static final Logger LOGGER = Logger.getGlobal();

    private final Settings settings;
    private final Sprite sprite;
    private Scene scene;
    private Stage stage;
    private ItemInfo[] items;
    private ItemCell[] cells;
    private Indicator indicator;
    private Button shopExit;
    private GUIInfo gui;
    private boolean isOpened;

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

    public Button getShopExit() {
        return shopExit;
    }


    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public ShopHandler(Settings settings, Sprite sprite) {
        this.settings = settings;
        this.sprite = sprite;
        loadItems();
    }

    private void loadItems() {
        Image img = new Image(Objects.requireNonNull(this.getClass().getResource(
                settings.getItemsPath())).toExternalForm());
        int[] arr = {4, 4};
        AnimationView view = new AnimationView(settings, "0");
        view.initView(img, arr);
        indicator = new Indicator();
        cells = new ItemCell[16];
        items = new ItemInfo[16];
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(Objects.requireNonNull(
                    this.getClass().getResource(settings.getItemsConfig())).toExternalForm());
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("item");
            for (int itr = 0; itr < nodeList.getLength(); itr++) {
                Node node = nodeList.item(itr);
                ItemInfo item = new ItemInfo();
                elementNodeInit(node, item);
                item.setStats("");
                itemDecreaseStats(item);
                view.setViewport(view.cellClips[itr]);
                item.setIcon(view);
                items[itr] = item;
                cells[itr] = new ItemCell(item, this.indicator);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOGGER.info("Item have been loaded successfully\n");
    }

    private void elementNodeInit(Node node, ItemInfo item) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) node;
            item.setId(Integer.parseInt(eElement.getElementsByTagName("id").item(0).getTextContent()));
            item.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
            item.setHpRegen(Integer.parseInt(
                    eElement.getElementsByTagName("hp_regen").item(0).getTextContent()));
            item.setWaterRegen(Integer.parseInt(
                    eElement.getElementsByTagName("water_regen").item(0).getTextContent()));
            item.setFoodRegen(Integer.parseInt(
                    eElement.getElementsByTagName("food_regen").item(0).getTextContent()));
            item.setHp(Integer.parseInt(
                    eElement.getElementsByTagName("hp").item(0).getTextContent()));
            item.setDmg(Integer.parseInt(
                    eElement.getElementsByTagName("dmg").item(0).getTextContent()));
            item.setSpeed(Double.parseDouble(
                    eElement.getElementsByTagName("speed").item(0).getTextContent()));
            item.setDesc(eElement.getElementsByTagName("desc").item(0).getTextContent());
            item.setPrice(Integer.parseInt(
                    eElement.getElementsByTagName("price").item(0).getTextContent()));
        }
    }

    private void itemDecreaseStats(ItemInfo item) {
        if (item.getHp() != -1) {
            item.stats += String.format("MAX HP: +%d", item.getHp());
        }
        if (item.getDmg() != -1) {
            item.stats += String.format("DMG: +%d", item.getDmg());
        }
        if (item.getSpeed() != -1) {
            item.stats += String.format("SPEED: +%f", item.getSpeed());
        }
        if (item.getFoodRegen() != -1) {
            item.stats += String.format("FOOD: +%d", item.getFoodRegen());
        }
        if (item.getWaterRegen() != -1) {
            item.stats += String.format("WATER: +%d", item.getWaterRegen());
        }
        if (item.getHpRegen() != -1) {
            item.stats += String.format("HP: +%d", item.getHpRegen());
        }
    }

    private void loadGui() {
        Rectangle imageRectangle = new Rectangle(575, 90);
        imageRectangle.setStroke(Color.WHITE);
        imageRectangle.setFill(Color.WHITE);
        imageRectangle.setWidth(99);
        imageRectangle.setHeight(99);

        Text title = new Text("SHOP");
        title.setFill(Color.WHITE);
        title.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        title.setX(575);
        title.setY(40);

        Button shopBuy = getShopButton();
        stage = new Stage();

        int buttonPadding = 25;
        int counter = 0;

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(buttonPadding));
        grid.setHgap(buttonPadding);
        grid.setVgap(buttonPadding);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                grid.add(cells[counter].getButton(), i, j);
                counter++;
            }
        }
        ImageView imageBg;
        imageBg = new ImageView(new Image(
                Objects.requireNonNull(this.getClass().getResource(settings.getShopBg2Path())).toExternalForm()));
        ImageView finalImgBg = imageBg;
        Group objects = new Group(imageRectangle, finalImgBg, title, shopBuy, shopExit, grid, indicator.getObjects());
        scene = new Scene(objects, 800, 400);
        stageInit(shopBuy);
        LOGGER.info("GUI has been loaded successfully\n");
    }

    private Button getShopButton() {
        Button shopBuy = new Button("BUY");
        setButton(shopBuy);
        shopBuy.setOnMouseEntered(event -> shopBuy.setTextFill(Color.DARKRED));
        shopBuy.setOnMouseExited(event -> shopBuy.setTextFill(Color.WHITE));
        shopBuy.setBackground(null);
        shopBuy.setTranslateX(715);
        shopBuy.setTranslateY(350);
        shopExit = new Button("X");
        setButton(shopExit);
        shopExit.setOnMouseEntered(event -> shopExit.setTextFill(Color.DARKRED));
        shopExit.setOnMouseExited(event -> shopExit.setTextFill(Color.WHITE));
        shopExit.setBackground(null);
        shopExit.setTranslateX(755);
        shopExit.setTranslateY(2);
        return shopBuy;
    }

    private void shopButtonInit(Button shopBuy) {
        shopBuy.setOnMouseClicked(event -> {
            if (sprite.money >= indicator.getItem().getPrice()) {
                if (indicator.getItem().getFoodRegen() == -1
                        && indicator.getItem().getHpRegen() == -1 && indicator.getItem().getWaterRegen() == -1) {
                    indicator.getCell().setDisable(true);
                }
                indicator.getItem().apply(sprite);
                gui.refresh(sprite);
                sprite.money -= indicator.getItem().getPrice();
            }
        });
        shopExit.setOnMouseClicked(event -> {
            stage.close();
            isOpened = false;
        });
    }

    private void stageInit(Button shopBuy) {
        stage.setScene(scene);
        stage.setX(100);
        stage.setY(200);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        shopButtonInit(shopBuy);
        stage.show();
    }

    public void open(GUIInfo gui) throws FileNotFoundException {
        isOpened = true;
        this.gui = gui;
        loadGui();
        indicator.setItem(items[0], indicator.getCell());

    }

    private void setButton(Button button) {
        button.setTextFill(Color.WHITE);
        button.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 18));
    }
}